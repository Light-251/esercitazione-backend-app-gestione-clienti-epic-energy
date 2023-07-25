package com.example.demo.repository.abstr;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;

import java.text.ParseException;
import java.util.List;

public abstract class ClienteRepositoryAbs {

    protected ConnectionHandler connectionHandler;

    public ClienteRepositoryAbs(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    /**
     * @param cliente da salvare nel Database
     * @return true se viene aggiunto correttamente, false altrimenti
     */
    public abstract boolean saveCliente(Cliente cliente);


    /**
     * @param id del CLiente da aggiornare
     * @return true se la modifica è andata a buon fine, false altrimenti
     */
    public abstract boolean updateClienteById(Integer id, Cliente cliente);

    /**
     * @param id del Cliente da rimouvere
     * @return true se la rimozione è andata a buon fine, false altrimenti
     */
    public abstract boolean removeClienteById(Integer id);

    /**
     *  Ritorna
     * @param nome si riferisce alla colonna nome_contatto
     * @param dataInserimentoIni data minore del range, si riferisce alla colonna data_inserimento
     * @param dataInserimentoFin data maggiore del range, si riferisce alla colonna data_inserimento
     * @param dataUltimoContattoIni data minore del range, si riferisce alla colonna data_ultimo_contatto
     * @param dataUltimoContattoFin data maggiore del range, si riferisce alla colonna data_ultimo_contatto
     * @param provincia si riferisce alla colonna provincia della tabella indirizzo
     * @param order permette di indicare in base a quale colonna ordinare i risultati
     * @param limit massimo di elementi per pagina
     * @param pagina  numero della pagina
     * @return la lista dei clienti in base ai filtri specificati, se non ne è specificato nessuno ritorna la lista completa
     * @throws ParseException se la data non viene inserita correttamente
     */
    public abstract ClienteDTO getClienti(
            String nome,
            String dataInserimentoIni,
            String dataInserimentoFin,
            String dataUltimoContattoIni,
            String dataUltimoContattoFin,
            String provincia,
            String order,
            Integer limit,
            Integer pagina) throws ParseException;

}
