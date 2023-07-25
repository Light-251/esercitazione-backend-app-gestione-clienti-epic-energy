package com.example.demo.repository.impl;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.dto.FatturaDTO;
import com.example.demo.model.Fattura;
import com.example.demo.repository.abstr.FatturaRepositoryAbs;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.QueryUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class FatturaRepositoryImpl extends FatturaRepositoryAbs {

    public FatturaRepositoryImpl(ConnectionHandler connectionHandler) {
        super(connectionHandler);
    }


    /**
     * @return la lista di tutte le fatture
     */
    @Deprecated
    @Override
    public List<Fattura> getAllFattura() {
        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM fattura;");

            List<Fattura> fatture = new ArrayList<>();
            while (resultSet.next()) {
                Fattura fattura = new Fattura();

                fattura.setId(resultSet.getInt("id_fattura"));
                fattura.setAnno(resultSet.getInt("anno"));
                fattura.setData(resultSet.getDate("data"));
                fattura.setImporto(resultSet.getDouble("importo"));
                fattura.setNumero(resultSet.getInt("numero"));
                fattura.setStato(resultSet.getString("stato"));
                fattura.setIdCliente(resultSet.getInt("id_cliente"));
//                fattura.setTipoFattura(resultSet.getString("tipo_fattura"));

                fatture.add(fattura);

                resultSet.close();
                statement.close();
                connectionHandler.closeConnection();
                return fatture;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeFatturaByd(Integer id) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement("DELETE FROM fattura WHERE id_fattura = ?;");
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    /**
     * @param fattura da salvare nel DB
     * @return true se viene salvata correttamente, false altrimenti
     */
    @Override
    public boolean saveFattura(Fattura fattura, Integer idCliente) {
        try {
            connectionHandler.openConnection();
            PreparedStatement preparedStatement = connectionHandler.getConnection().prepareStatement("INSERT INTO fattura (anno, data, numero, stato, id_cliente, importo) VALUES (?,?,?,?,?,?);");

            preparedStatement.setInt(1, fattura.getAnno());
            preparedStatement.setDate(2, new Date(fattura.getData().getTime()));
            preparedStatement.setInt(3, fattura.getNumero());
            preparedStatement.setString(4, fattura.getStato());
            preparedStatement.setInt(5, idCliente);
            preparedStatement.setDouble(6, fattura.getImporto());

            int rowsModified = preparedStatement.executeUpdate();

            preparedStatement.close();
            connectionHandler.closeConnection();

            return rowsModified > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateFattura(Integer id, Integer idCLiente, Fattura fattura) {

        try {
            connectionHandler.openConnection();
            Statement statement = connectionHandler.getConnection().createStatement();

            String sql = QueryUtils.dinamicQueryUpdateFattura(id, fattura);
            int rowsAffected = statement.executeUpdate(sql);

            statement.close();
            connectionHandler.closeConnection();
            return rowsAffected > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    @Override
    public FatturaDTO getFatture(String nomeCliente, String stato, String dataIni, String dataFin, String anno, Double importoMin,
                                 Double importoMax, Integer idCliente, String order, Integer limit, Integer pagina) throws ParseException {

        java.util.Date dataIniParsed = DateUtils.stringToSqlDate(dataIni);
        java.util.Date dataFinParsed = DateUtils.stringToSqlDate(dataFin);

//        String sql = queryFiltraggioDatiFattura(nomeCliente, stato, dataIniParsed, dataFinParsed, anno, importoMin, importoMax, idCliente, order, limit, pagina);
        List<Object> listaParametri = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM fattura");

        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            sql.append(" LEFT JOIN cliente ON cliente.id_cliente = fattura.id_cliente  WHERE 1=1 AND cliente.nome_contatto = ? ");
            listaParametri.add(nomeCliente);
        } else
            sql.append(" WHERE 1=1 ");

        if (stato != null && !stato.isEmpty()) {
            listaParametri.add(stato);
            sql.append("AND stato = ? ");
        }

        if (importoMin != null && importoMax != null) {
            listaParametri.add(importoMin);
            listaParametri.add(importoMax);
            sql.append("AND importo BETWEEN ? AND ? ");
        }

        if (dataIni != null && dataFin != null) {
            listaParametri.add(dataIni);
            listaParametri.add(dataFin);
            sql.append("AND data BETWEEN ? AND ? ");
        }

        if (anno != null && !anno.isEmpty()) {
            listaParametri.add(anno);
            sql.append("AND anno = ? ");
        }

        if (idCliente != null) {
            listaParametri.add(idCliente);
            sql.append(" AND fattura.id_cliente = ? ");
        }

        if (order != null) {
//            listaParametri.add(order);
            sql.append(" ORDER BY ").append(QueryUtils.convertColumn("fattura", order)).append(" ");
        }

        if (limit == null)
            limit = 5;
        if (pagina == null)
            pagina = 0;

        sql.append(" LIMIT ").append(limit);
        sql.append(" OFFSET ").append(pagina * limit);
        sql.append(";");


        try {
            connectionHandler.openConnection();
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sql.toString());

            popolaPreparesStatement(listaParametri, statement);
//            riempiQuery(statement, nomeCliente, stato, dataIniParsed, dataFinParsed, anno, importoMin, importoMax, idCliente);

            System.out.println(statement.toString());

            ResultSet resultSet = statement.executeQuery();

            List<Fattura> fatture = getListaFatture(resultSet);
            if (fatture.size() == 0)
                return null;

            resultSet.close();
            statement.close();


            String sqlCount = QueryUtils.queryContaRisultatiFattura(nomeCliente, stato, dataIniParsed, dataFinParsed, anno, importoMin, importoMax, idCliente);

            PreparedStatement countStatement = connectionHandler.getConnection().prepareStatement(sqlCount);
            riempiQuery(countStatement, nomeCliente, stato, dataIniParsed, dataFinParsed, anno, importoMin, importoMax, idCliente);

            ResultSet countResultSet = countStatement.executeQuery();
            countResultSet.next();
            int numeroRisultati = countResultSet.getInt("count");

            countResultSet.close();
            countStatement.close();
            connectionHandler.closeConnection();

            FatturaDTO fatturaDTO = new FatturaDTO();
            fatturaDTO.setFatture(fatture);
            fatturaDTO.setPagina(Objects.requireNonNullElse(pagina, 0));
            fatturaDTO.setElementiPerPagina(fatture.size());
            fatturaDTO.setElementiTotali(numeroRisultati);

            return fatturaDTO;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private List<Fattura> getListaFatture(ResultSet resultSet) throws SQLException {
        List<Fattura> fatture = new ArrayList<>();

        while (resultSet.next()) {
            Fattura fattura = new Fattura();

            fattura.setId(resultSet.getInt("id_fattura"));
            fattura.setAnno(resultSet.getInt("anno"));
            fattura.setData(resultSet.getDate("data"));
            fattura.setImporto(resultSet.getDouble("importo"));
            fattura.setNumero(resultSet.getInt("numero"));
            fattura.setStato(resultSet.getString("stato"));
            fattura.setIdCliente(resultSet.getInt("id_cliente"));

            fatture.add(fattura);
        }

        return fatture;
    }

    private void popolaPreparesStatement(List<Object> listaArgomenti, PreparedStatement preparedStatement) throws SQLException {
        int index = 1;
        for (Object obj : listaArgomenti) {
            preparedStatement.setObject(index++, obj);
        }
    }


    private void riempiQuery(PreparedStatement preparedStatement, String nomeCliente, String stato, java.util.Date dataIni,
                             java.util.Date dataFin, String anno, Double importoMin, Double importoMax, Integer idCLiente) throws SQLException {

        int posizione = 0;

        if (nomeCliente != null)
            preparedStatement.setString(++posizione, nomeCliente);

        if (stato != null)
            preparedStatement.setString(++posizione, stato);

        if (importoMin != null && importoMax != null) {
            preparedStatement.setDouble(++posizione, importoMin);
            preparedStatement.setDouble(++posizione, importoMax);
        }
        if (dataIni != null && dataFin != null) {
            preparedStatement.setDate(++posizione, new Date(dataIni.getTime()));
            preparedStatement.setDate(++posizione, new Date(dataFin.getTime()));
        }

        if (anno != null)
            preparedStatement.setString(++posizione, anno);

        if (idCLiente != null)
            preparedStatement.setInt(++posizione, idCLiente);

    }


}
