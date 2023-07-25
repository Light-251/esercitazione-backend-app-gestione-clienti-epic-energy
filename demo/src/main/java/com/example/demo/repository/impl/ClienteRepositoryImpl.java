package com.example.demo.repository.impl;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.abstr.ClienteRepositoryAbs;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ClienteRepositoryImpl extends ClienteRepositoryAbs {


    public ClienteRepositoryImpl(ConnectionHandler connectionHandler) {
        super(connectionHandler);
    }

    /**
     * @param cliente da salvare nel DB
     * @return true se viene salvato correttamente, false altrimenti
     */
    @Override
    public boolean saveCliente(Cliente cliente) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement(
                    "INSERT INTO cliente (ragione_sociale, partita_iva, email, data_inserimento, data_ultimo_contatto, fatturato_annuale, pec, telefono, email_contatto, nome_contatto, cognome_contatto, telefono_contatto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");

            preparedStatement.setString(1, cliente.getRagioneSociale());
            preparedStatement.setString(2, cliente.getPartitaIva());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setDate(4, new Date(cliente.getDataInserimento().getTime()));
            preparedStatement.setDate(5, new Date(cliente.getDataUltimoContatto().getTime()));
            preparedStatement.setDouble(6, cliente.getFatturatoAnnuale());
            preparedStatement.setString(7, cliente.getPec());
            preparedStatement.setString(8, cliente.getTelefono());
            preparedStatement.setString(9, cliente.getEmailContatto());
            preparedStatement.setString(10, cliente.getNomeContatto());
            preparedStatement.setString(11, cliente.getCognomeContatto());
            preparedStatement.setString(12, cliente.getTelefonoContatto());

            int rowsModified = preparedStatement.executeUpdate();

            preparedStatement.close();
            connectionHandler.closeConnection();

            return rowsModified > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * @param id      del CLiente da aggiornare
     * @param cliente è l'oggetto con i campi aggiornati da salvare nel DB
     * @return true se la modifica è andata buon fine, false altrimenti
     */
    @Override
    public boolean updateClienteById(Integer id, Cliente cliente) {
        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();

            String sql = QueryUtils.dinamicQueryUpdateCliente(id, cliente);
            int rowsAffected = statement.executeUpdate(sql);

            statement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * @param id del Cliente da rimuovere
     * @return true se viene rimosso con successo, false altrimenti
     */
    @Override
    public boolean removeClienteById(Integer id) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement("DELETE FROM cliente WHERE id_cliente = ?;");
            preparedStatement.setInt(1, id);
            PreparedStatement preparedStatementFatture = connectionHandler.getConnection().prepareStatement("DELETE FROM fattura WHERE id_cliente = ?;");
            preparedStatementFatture.setInt(1, id);
            PreparedStatement preparedStatementIndirizzo = connectionHandler.getConnection().prepareStatement("DELETE FROM indirizzo WHERE id_cliente = ?;");
            preparedStatementIndirizzo.setInt(1, id);

            int rowsModified = preparedStatementFatture.executeUpdate();
            rowsModified += preparedStatementIndirizzo.executeUpdate();
            rowsModified += preparedStatement.executeUpdate();

            preparedStatementIndirizzo.close();
            preparedStatementFatture.close();
            preparedStatement.close();
            connectionHandler.closeConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public ClienteDTO getClienti(String nome, String dataInserimentoIni, String dataInserimentoFin, String dataUltimoContattoIni,
                                 String dataUltimoContattoFin, String provincia, String order, Integer limit, Integer pagina) throws ParseException {

        java.sql.Date inserimentoIni = DateUtils.stringToSqlDate(dataInserimentoIni);
        java.sql.Date inserimentoFin = DateUtils.stringToSqlDate(dataInserimentoFin);

        java.sql.Date ultimoContattoIni = DateUtils.stringToSqlDate(dataUltimoContattoIni);
        java.sql.Date ultimoContattoFin = DateUtils.stringToSqlDate(dataUltimoContattoFin);

//        String sql = QueryUtils.queryFiltraggioDatiCliente(nome, inserimentoIni, inserimentoFin, ultimoContattoIni, ultimoContattoFin, provincia, order, limit, pagina);

        List<Object> listaParametri = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM cliente");

        if (provincia != null && !provincia.isEmpty()) {
            listaParametri.add(provincia);
            sql.append(" LEFT JOIN indirizzo ON cliente.id_cliente = indirizzo.id_cliente  WHERE 1=1 AND provincia = ? AND indirizzo.tipo = 'SEDE LEGALE' ");
        } else
            sql.append(" WHERE 1=1 ");

        if (nome != null && !nome.isEmpty()) {
            listaParametri.add(nome);
            sql.append("AND nome_contatto LIKE ? ");
        }

        if (dataInserimentoIni != null && dataInserimentoFin != null) {
            listaParametri.add(inserimentoIni);
            listaParametri.add(inserimentoFin);
            sql.append("AND data_inserimento BETWEEN ? AND ? ");
        }

        if (dataUltimoContattoIni != null && dataUltimoContattoFin != null) {
            listaParametri.add(ultimoContattoIni);
            listaParametri.add(ultimoContattoFin);
            sql.append("AND data_ultimo_contatto BETWEEN ?  AND ? ");
        }

        if (order != null)
            sql.append(" ORDER BY ").append(QueryUtils.convertColumn("cliente", order)).append(" ");

        if (limit == null)
            limit = 5;
        if (pagina == null)
            pagina = 0;

        sql.append(" LIMIT ").append(limit);
        sql.append(" OFFSET ").append(pagina * limit);
        sql.append(";");

        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement(sql.toString());
            popolaPreparesStatement(listaParametri, preparedStatement);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Cliente> clienti = getListaClienti(resultSet);
            if (clienti.size() == 0)
                return null;

            resultSet.close();
            preparedStatement.close();

            String sqlCount = QueryUtils.queryContaRisultatiCliente(nome, inserimentoIni, inserimentoFin, ultimoContattoIni, ultimoContattoFin, provincia);

            System.out.println(sqlCount); //Conta risultati

            PreparedStatement countStatement = connectionHandler.getConnection().prepareStatement(sqlCount);

            riempiQuery(countStatement, nome, inserimentoIni, inserimentoFin, ultimoContattoIni, ultimoContattoFin, provincia);

            ResultSet countResultSet = countStatement.executeQuery();

            int numeroRisultati = 0;
            if (countResultSet.next())
                numeroRisultati = countResultSet.getInt("count");

            countStatement.close();
            countResultSet.close();
            connectionHandler.closeConnection();

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setClienti(clienti);
            clienteDTO.setNumeroPagina(Objects.requireNonNullElse(pagina, 0));
            clienteDTO.setElementiPerPagina(clienti.size());
            clienteDTO.setElementiTotali(numeroRisultati);

            return clienteDTO;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private void popolaPreparesStatement(List<Object> listaArgomenti, PreparedStatement preparedStatement) throws SQLException {
        int index = 1;
        for (Object obj : listaArgomenti) {
            preparedStatement.setObject(index++, obj);
        }
    }

    private void riempiQuery(PreparedStatement preparedStatement, String nome, java.util.Date inserimentiIni, java.util.Date inserimentoFin,
                             java.util.Date ultimoContattoIni, java.util.Date ultimoContattoFin, String provincia) throws SQLException {

        int posizione = 0;

        if (provincia != null)
            preparedStatement.setString(++posizione, provincia);

        if (nome != null)
            preparedStatement.setString(++posizione, "%" + nome + "%");

        if (inserimentiIni != null && inserimentoFin != null) {
            preparedStatement.setDate(++posizione, new Date(inserimentiIni.getTime()));
            preparedStatement.setDate(++posizione, new Date(inserimentoFin.getTime()));
        }
        if (ultimoContattoIni != null && ultimoContattoFin != null) {
            preparedStatement.setDate(++posizione, new Date(ultimoContattoIni.getTime()));
            preparedStatement.setDate(++posizione, new Date(ultimoContattoFin.getTime()));
        }

    }

    private List<Cliente> getListaClienti(ResultSet resultSet) throws SQLException {

        List<Cliente> clienti = new ArrayList<>();

        while (resultSet.next()) {
            Cliente cliente = new Cliente();

            cliente.setId(resultSet.getInt("id_cliente"));
            cliente.setRagioneSociale(resultSet.getString("ragione_sociale"));
            cliente.setPartitaIva(resultSet.getString("partita_iva"));
            cliente.setEmail(resultSet.getString("email"));
            cliente.setDataInserimento(resultSet.getDate("data_inserimento"));
            cliente.setDataUltimoContatto(resultSet.getDate("data_ultimo_contatto"));
            cliente.setFatturatoAnnuale(resultSet.getDouble("fatturato_annuale"));
            cliente.setPec(resultSet.getString("pec"));
            cliente.setTelefono(resultSet.getString("telefono"));
            cliente.setEmailContatto(resultSet.getString("email_contatto"));
            cliente.setNomeContatto(resultSet.getString("nome_contatto"));
            cliente.setCognomeContatto(resultSet.getString("cognome_contatto"));
            cliente.setTelefonoContatto(resultSet.getString("telefono_contatto"));

            clienti.add(cliente);
        }
        return clienti;
    }

}
