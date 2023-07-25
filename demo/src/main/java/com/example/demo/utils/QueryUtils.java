package com.example.demo.utils;

import com.example.demo.model.Cliente;
import com.example.demo.model.Fattura;
import com.example.demo.model.Indirizzo;
import com.example.demo.model.Utente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryUtils {

    /**
     * @param id        dell'indirizzo da modificare
     * @param indirizzo contiene i campi da modificare
     * @return Query SQL che modifica solo i campi di Indirizzo che non sono null
     */
    public static String dinamicQueryUpdateIndirizzo(Integer id, Indirizzo indirizzo) {

        StringBuilder builder = new StringBuilder("UPDATE indirizzo SET");

        boolean first = true;

        if (indirizzo.getVia() != null) {
            builder.append(" via = '").append(indirizzo.getVia()).append("' ");
            first = false;
        }
        if (indirizzo.getCivico() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" civico = '").append(indirizzo.getCivico()).append("' ");
        }
        if (indirizzo.getLocalita() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" localita = '").append(indirizzo.getLocalita()).append("' ");
        }
        if (indirizzo.getCap() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" cap = '").append(indirizzo.getCap()).append("' ");
        }
        if (indirizzo.getTipo() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" tipo = '").append(indirizzo.getTipo()).append("' ");
        }
        if (indirizzo.getProvincia() != null) {
            if (!first)
                builder.append(", ");
            builder.append(" provincia = '").append(indirizzo.getProvincia()).append("' ");
        }

        if (indirizzo.getIdCliente() != null) {
            builder.append(" id_cliente = '").append(indirizzo.getIdCliente()).append("' ");
        }

        builder.append("WHERE id_indirizzo = ").append(id).append(";");

        return builder.toString();
    }

    /**
     * @param id     dell'utente da modificare
     * @param utente contiene i campi da modificare
     * @return Query SQL che modifica solo i campi di utente che non sono null
     */
    public static String dinamicQueryUpdateUtente(Integer id, Utente utente) {

        StringBuilder builder = new StringBuilder("UPDATE utente SET");

        boolean first = true;

        if (utente.getUsername() != null) {
            builder.append(" username = '").append(utente.getUsername()).append("' ");
            first = false;
        }
        if (utente.getEmail() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" email = '").append(utente.getEmail()).append("' ");
        }
        if (utente.getPassword() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" password = '").append(utente.getPassword()).append("' ");
        }
        if (utente.getNome() != null) {
            if (!first)
                builder.append(", ");
            builder.append(" nome = '").append(utente.getNome()).append("' ");
        }
        if (utente.getCognome() != null) {
            builder.append(" cognome = '").append(utente.getCognome()).append("' ");
        }

        builder.append("WHERE id_utente = ").append(id).append(";");

        return builder.toString();
    }

    /**
     * @param id      dell'fattura da modificare
     * @param fattura contiene i campi da modificare
     * @return Query SQL che modifica solo i campi di fattura che non sono null
     */
    public static String dinamicQueryUpdateFattura(Integer id, Fattura fattura) {

        StringBuilder builder = new StringBuilder("UPDATE fattura SET");

        boolean first = true;

        if (fattura.getAnno() != null) {
            builder.append(" anno = '").append(fattura.getAnno()).append("' ");
            first = false;
        }
        if (fattura.getData() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" data = '").append(fattura.getData()).append("' ");
        }
        if (fattura.getImporto() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" importo = '").append(fattura.getImporto()).append("' ");
        }
        if (fattura.getNumero() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" numero = '").append(fattura.getNumero()).append("' ");
        }
        if (fattura.getStato() != null) {
            if (!first)
                builder.append(", ");
            builder.append(" stato = '").append(fattura.getStato()).append("' ");
        }
        if (fattura.getIdCliente() != null) {
            builder.append(" id_cliente = '").append(fattura.getIdCliente()).append("' ");
        }

        builder.append("WHERE id_fattura = ").append(id).append(";");

        return builder.toString();
    }

    /**
     * @param id      dell'cliente da modificare
     * @param cliente contiene i campi da modificare
     * @return Query SQL che modifica solo i campi di cliente che non sono null
     */
    public static String dinamicQueryUpdateCliente(Integer id, Cliente cliente) {

        StringBuilder builder = new StringBuilder("UPDATE cliente SET");

        boolean first = true;

        if (cliente.getRagioneSociale() != null) {
            builder.append(" ragione_sociale = '").append(cliente.getRagioneSociale()).append("' ");
            first = false;
        }
        if (cliente.getPartitaIva() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" partita_iva = '").append(cliente.getPartitaIva()).append("' ");
        }
        if (cliente.getEmail() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" email = '").append(cliente.getEmail()).append("' ");
        }
        if (cliente.getDataInserimento() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" data_inserimento = '").append(cliente.getDataInserimento()).append("' ");
        }
        if (cliente.getDataUltimoContatto() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" data_ultimo_contatto = '").append(cliente.getDataUltimoContatto()).append("' ");
        }
        if (cliente.getPec() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" pec = '").append(cliente.getPec()).append("' ");
        }
        if (cliente.getTelefono() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" telefono = '").append(cliente.getTelefono()).append("' ");
        }
        if (cliente.getEmailContatto() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" email_contatto = '").append(cliente.getEmailContatto()).append("' ");
        }
        if (cliente.getNomeContatto() != null) {
            if (!first)
                builder.append(", ");
            else
                first = false;
            builder.append(" nome_contatto = '").append(cliente.getNomeContatto()).append("' ");
        }
        if (cliente.getCognomeContatto() != null) {
            if (!first)
                builder.append(", ");
            builder.append(" cognome_contatto = '").append(cliente.getCognomeContatto()).append("' ");
        }
        if (cliente.getTelefonoContatto() != null) {
            builder.append(" telefono_contatto = '").append(cliente.getTelefonoContatto()).append("' ");
        }

        builder.append("WHERE id_cliente = ").append(id).append(";");

        return builder.toString();
    }

    @Deprecated
    public static String queryFiltraggioDatiCliente2(String nome, Date dataInserimentoIni, Date dataInserimentoFin, Date dataUltimoContattoIni,
                                                     Date dataUltimoContattoFin, String provincia, Integer limit, Integer offset) {
        StringBuilder builder = new StringBuilder("SELECT * FROM cliente LEFT JOIN indirizzo ON cliente.id_cliente = indirizzo.id_cliente  WHERE 1=1 ");

        boolean first = true;

        if (nome != null && !nome.isEmpty()) {
            builder.append(" nome_contatto LIKE '%").append(nome).append("%' ");
            first = false;
        }
        if (dataInserimentoIni != null && dataInserimentoFin != null) {
            if (!first)
                builder.append(" AND ");
            else
                first = false;
            builder.append(" data_inserimento BETWEEN ? AND ? ");
        }
        if (dataUltimoContattoIni != null && dataUltimoContattoFin != null) {
            if (!first)
                builder.append(" AND ");
            else
                first = false;
            builder.append(" data_ultimo_contatto BETWEEN ?  AND ? ");
        }
        if (provincia != null && !provincia.isEmpty()) {
            if (!first)
                builder.append(" AND ");
            builder.append("provincia = '").append(provincia).append("' AND indirizzo.tipo = 'SEDE LEGALE'");
        }
        if (limit != null && limit >= 0) {
            builder.append(" LIMIT ").append(limit);
            if (offset != null && offset > 0)
                builder.append(" OFFSET ").append(offset);
        }

        return builder.append(";").toString();
    }

    /**
     * Crea una Query dinamicamente per filtrare i dati in base ai parametri passati
     *
     * @param nomeCliente nome del Cliente
     * @param stato       stato della fattura
     * @param dataIni     data iniziale del range da filtrare
     * @param dataFin     data finale del range da filtrare
     * @param anno        anno della fattura
     * @param importoMin  importo minimo del range da filtrare
     * @param importoMax  importo massimo del range da filtrare
     * @param order       ordinamento
     * @param limit       limite di record da ritornare
     * @param offset      offset dal quale partire
     * @return Query per eseguire la selezione dei dati nel Database
     */
    public static String queryFiltraggioDatiFattura(String nomeCliente, String stato, java.util.Date dataIni, java.util.Date dataFin, String anno, Double importoMin,
                                                    Double importoMax, Integer idCliente, String order, Integer limit, Integer offset) {
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
            sql.append(" ORDER BY ").append(QueryUtils.convertColumn("fattura", order)).append(" ");
        }

        if (limit != null && limit >= 0) {
            sql.append(" LIMIT ").append(limit);
            if (offset != null && offset > 0)
                sql.append(" OFFSET ").append(offset * limit);
        } else {
            sql.append(" LIMIT 5 OFFSET 0 ");
        }

        return sql.append(";").toString();
    }

    /**
     * @param nomeCliente si riferisce alla colonna nome_cliente
     * @param stato       si riferisce alla colonna stato
     * @param dataIni     data minima del range del filtro si riferisce alla colonna data_inserimento
     * @param dataFin     data massima del range del filtro si riferisce alla colonna data_inserimento
     * @param anno        si riferisce alla colonna anno
     * @param importoMin  data minima del range del filtro si riferisce alla colonna data_inserimento
     * @param importoMax  data massima del range del filtro si riferisce alla colonna data_inserimento
     * @param idCliente   si riferisce alla colonna id_cliente
     * @return Una Query per contare i record in base ai filtri applicati
     */
    public static String queryContaRisultatiFattura(String nomeCliente, String stato, Date dataIni, Date dataFin, String anno, Double importoMin,
                                                    Double importoMax, Integer idCliente) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM fattura  ");

        if (nomeCliente != null)
            query.append(" LEFT JOIN cliente ON cliente.id_cliente = fattura.id_cliente  WHERE 1=1 AND cliente.nome_contatto = ?  ");
        else
            query.append(" WHERE 1=1");

        if (stato != null)
            query.append(" AND stato = ? ");
        if (importoMin != null & importoMax != null)
            query.append(" AND importo BETWEEN ? AND ? ");
        if (dataIni != null & dataFin != null)
            query.append(" AND data BETWEEN ? AND ? ");
        if (anno != null)
            query.append(" AND anno = ? ");
        if (idCliente != null)
            query.append(" AND id_cliente = ? ");

        return query.append("; ").toString();
    }

    /**
     * Crea una Query dinamicamente per filtrare i dati in base ai parametri passati
     *
     * @param nome                  nome del CLiente
     * @param dataInserimentoIni    data iniziale inserimento del range da filtrare
     * @param dataInserimentoFin    data finale inserimento del range da filtrare
     * @param dataUltimoContattoIni data iniziale dell'ultimo contatto
     * @param dataUltimoContattoFin data finale dell'ultimo contatto
     * @param provincia             provincia del Cliente
     * @param order                 ordinamento
     * @param limit                 numero massimo di record da visualizzare
     * @param offset                posizione dei record dal quale partire
     * @return Query per eseguire la selezione dei dati nel Database
     */
    public static String queryFiltraggioDatiCliente(String nome, Date dataInserimentoIni, Date dataInserimentoFin, Date dataUltimoContattoIni,
                                                    Date dataUltimoContattoFin, String provincia, String order, Integer limit, Integer offset) {
        StringBuilder builder = new StringBuilder("SELECT * FROM cliente");

        if (provincia != null && !provincia.isEmpty()) {
            builder.append(" LEFT JOIN indirizzo ON cliente.id_cliente = indirizzo.id_cliente  WHERE 1=1 AND provincia = ? AND indirizzo.tipo = 'SEDE LEGALE' ");
        } else
            builder.append(" WHERE 1=1 ");

        if (nome != null && !nome.isEmpty()) {
            builder.append("AND nome_contatto LIKE ? ");
        }

        if (dataInserimentoIni != null && dataInserimentoFin != null) {
            builder.append("AND data_inserimento BETWEEN ? AND ? ");
        }

        if (dataUltimoContattoIni != null && dataUltimoContattoFin != null) {
            builder.append("AND data_ultimo_contatto BETWEEN ?  AND ? ");
        }

        if (order != null)
            builder.append(" ORDER BY ").append(convertColumn("cliente", order)).append(" ");


        if (limit != null && limit >= 0) {
            builder.append(" LIMIT ").append(limit);
            if (offset != null && offset > 0)
                builder.append(" OFFSET ").append(offset * limit);
        } else
            builder.append(" LIMIT 5 OFFSET 0 ");

        return builder.append(";").toString();
    }

    /**
     * @param nome                  si riferisce alla colonna nome_contatto
     * @param dataInserimentoIni    data minima del range del filtro si riferisce alla colonna data_inserimento
     * @param dataInserimentoFin    data massima del range del filtro si riferisce alla colonna data_inserimento
     * @param dataUltimoContattoIni data minima del range del filtro si riferisce alla colonna data_inserimento
     * @param dataUltimoContattoFin data massima del range del filtro si riferisce alla colonna data_inserimento
     * @param provincia             si riferisce alla colonna provincia della tabella indirizzo
     * @return Una Query per contare i record in base ai filtri applicati
     */
    public static String queryContaRisultatiCliente(String nome, Date dataInserimentoIni, Date dataInserimentoFin, Date dataUltimoContattoIni,
                                                    Date dataUltimoContattoFin, String provincia) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM cliente ");

        if (provincia != null && !provincia.isEmpty())
            query.append(" LEFT JOIN indirizzo ON cliente.id_cliente = indirizzo.id_cliente  WHERE 1=1 AND provincia = ? AND indirizzo.tipo = 'SEDE LEGALE' ");
        else
            query.append(" WHERE 1=1 ");

        if (nome != null && !nome.isEmpty())
            query.append("AND nome_contatto LIKE ? ");

        if (dataInserimentoIni != null && dataInserimentoFin != null)
            query.append("AND data_inserimento BETWEEN ? AND ? ");

        if (dataUltimoContattoIni != null && dataUltimoContattoFin != null)
            query.append("AND data_ultimo_contatto BETWEEN ?  AND ? ");

        return query.append("; ").toString();
    }

    /**
     * Converte il nome di una colonna in formato camelCase in formato snake_case
     *
     * @param columnName nome della colonna in formato camelCase
     * @return nome della colonna del Database
     */
    public static String convertColumn(String nomeTabella, String columnName) {
        String res = "";

        switch (columnName) {
            case "idFattura":
                res = nomeTabella + ".id_fattura";
                break;
            case "idCliente":
                res = nomeTabella + ".id_cliente";
                break;
            case "idIndirizzo":
                res = nomeTabella + ".id_indirizzo";
                break;
            case "ragioneSociale":
                res = "ragione_sociale";
                break;
            case "partitaIva":
                res = "partita_iva";
                break;
            case "dataInserimento":
                res = "data_inserimento";
                break;
            case "dataUltimoContatto":
                res = "data_ultimo_contatto";
                break;
            case "fatturatoAnnuale":
                res = "fatturato_annuale";
                break;
            case "emailContatto":
                res = "email_contatto";
                break;
            case "nomeContatto":
                res = "nome_contatto";
                break;
            case "cognomeContatto":
                res = "cognome_contatto";
                break;
            case "telefonoContatto":
                res = "telefono_contatto";
                break;
            default:
                res = columnName;
        }

        return res;
    }

}
