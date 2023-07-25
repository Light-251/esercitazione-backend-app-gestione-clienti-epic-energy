package com.example.demo.repository.abstr;

import com.example.demo.connection.ConnectionHandler;
import com.example.demo.dto.FatturaDTO;
import com.example.demo.model.Fattura;

import java.text.ParseException;
import java.util.List;

public abstract class FatturaRepositoryAbs {

    protected ConnectionHandler connectionHandler;

    public FatturaRepositoryAbs(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    /**
     * Permette di salvare una Fattura nel Database
     * @param fattura oggetto contenente i dati da salvare
     * @param idCliente id del Cliente che è collegato alla fattura
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public abstract boolean saveFattura(Fattura fattura, Integer idCliente);

    /**
     * Permette di modificare i dati di una Fattura presente nel DB
     * @param id id della Fattura da modificare
     * @param idCliente id del Cliente che è collegato alla Fattura
     * @param fattura oggetto che contiene i dati da aggiornare, i campi null vengono ignorati
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public abstract boolean updateFattura(Integer id, Integer idCliente, Fattura fattura);

    /**
     * DA CANCELLARE
     * @return
     */
    @Deprecated
    public abstract List<Fattura> getAllFattura();

    /**
     * Permette di rimuovere una Fattura dal DB
     * @param id id della Fattura da rimuovere
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public abstract boolean removeFatturaByd(Integer id);

    /**
     * Permette di ottenere una lista di Fatture specificando dei filtri di ricerca
     * se non viene specificato nessun filtro ritorna la lista di tutte le Fatture
     *
     * @param nomeCliente si riferisce alla colonna nome_cliente
     * @param stato si riferisce alla colonna stato
     * @param dataIni data minore del range, si riferisce alla colonna data
     * @param dataFin data maggiore del range, si riferisce alla colonna data
     * @param anno si ri ferise alla colonna anno
     * @param importoMin importo minore del range, si riferisce alla colonna importo
     * @param importoMax importo maggiore del range, si riferisce alla colonna importo
     * @param idCliente si riferisce alla colonna id_cliente
     * @param order permette di indicare in base a quale colonna ordinare i risultati
     * @param limit massimo di elementi per pagina
     * @param pagina numero della pagina
     * @return lista delle Fatture in base ai filtri specificati, se non ne viene specificato nessuno ritorna la lista completa
     * @throws ParseException se la data non viene inserita correttamente
     */
    public abstract FatturaDTO getFatture(String nomeCliente,
                                          String stato,
                                          String dataIni,
                                          String dataFin,
                                          String anno,
                                          Double importoMin,
                                          Double importoMax,
                                          Integer idCliente,
                                          String order,
                                          Integer limit,
                                          Integer pagina) throws ParseException;


}
