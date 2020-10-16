package com.example.fro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/*
* Funcao responsvel por gerenciar o mock de dados sobre plantas
* */
public class BancoDePlantas {

    Hashtable<String, Planta> bancoDePlantas = new Hashtable<String, Planta>();;

    public BancoDePlantas() throws JSONException {
        /*!< Mock */
        /*
        * - Plantas:
        * https://www.hometeka.com.br/inspire-se/22-plantas-para-cultivar-em-apartamento/
        * https://www.decorfacil.com/plantas-para-apartamento/
        * - Informações das plantas:
        * https://www.sitiodamata.com.br/
        * https://extra.globo.com/mulher/decoracao-e-jardim/
        * */

        Planta samambaia = new Planta();
        samambaia.setNome("Samambaia");

        Planta rafia = new Planta();
        rafia.setNome("Palmeira");

        Planta lancaSaoJorge = new Planta();
        lancaSaoJorge.setNome("Lança de São Jorge");
        lancaSaoJorge.setFrequenciaDeRegamento("Sua irrigação deve acontecer uma vez a cada uma ou duas semanas com 100ml de água, sem jogar água diretamente sobre as folhas.");
        lancaSaoJorge.setLocalAdequadoParaPlantio("Pode ser colocada em partes internas da casa ou em varandas em que fique muito exposta ao sol pois é muito resistente.");
        lancaSaoJorge.setFertilizantesRecomendados("A planta não precisa ser adubada com frequência. Mas, se ficar doente, use o produto NPK 4-14-8.");
        lancaSaoJorge.setPoda("Poda não necessária, se as touceiras atingirem dimensões indesejadas, faça o replantio dividindo.");
        lancaSaoJorge.setPragasComuns("Os maiores problemas relatados são as lagartas de mariposas e tripes, que podem invadir as estufas a partir de plantas infestantes que possam estar próximas. As lagartas podem ser detectadas pela presença de seus excrementos ou pelo dano que causam, como grandes buracos no centro ou ao longo das bordas das folhas. Já os danos provocados por tripes podem ser folhas enroladas e distorcidas, com cicatrizes cinza-prateado ou calosidades onde o inseto se alimentou; além disso, tripes podem transmitir viroses para muitos espécimes ornamentais.");
        lancaSaoJorge.setAltura("Pode ter até aproximadamente dois metros de altura.");
        lancaSaoJorge.setTempoDeVida("Ela dura entre um e três anos.");
        lancaSaoJorge.setPreco("Aproximadamente R$ 25,00 a muda.");
        lancaSaoJorge.setOutrasInformacoes("A Lança-de-São-Jorge recebeu este nome porque suas folhagens são pontudas e compridas. Elas podem ser retas ou trançadas, com uma enrolada na outra. Para muita gente, a planta traz proteção ao lar.");

        bancoDePlantas.put("fern", samambaia);
        bancoDePlantas.put("palm", rafia);
        bancoDePlantas.put("saint barbara sword", lancaSaoJorge);
    }


    public Planta identificarPlanta(JSONArray nomesComuns) {
        System.out.println(nomesComuns);
        Planta planta = bancoDePlantas.get("saint barbara sword");
        return planta;
    }

}
