package com.example.fro;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Referencia (chamar API em Java): https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android/
 * Referencia (exemplo de chamada Java para API): https://github.com/Plant-id/Plant-id-API/pull/6/commits/4da59702bc3b9c9c85e81b8c2503fda5030df33c
 * **/
public class PlantId extends AsyncTask<Void, Void, String> {

    private String imagemPlantaBase64;

    private String urlString = "https://api.plant.id/v2/identify";
    private String apiKey = "5P203X1zTMgsllLH52vLIlVHxro52vlU49NxPicnt3ztFMZacO";

    public PlantId(String imagemPlantaBase64) {
        this.imagemPlantaBase64 = imagemPlantaBase64;
    }

    private JSONObject criarMensagem() throws JSONException {
        /*!< Adicionando chave */
        JSONObject data = new JSONObject();
        data.put("api_key", apiKey);

        /*!< Adicionado modificadores */
        JSONArray modifiers = new JSONArray()
                .put("crops_fast")
                .put("similar_images");
        data.put("modifiers", modifiers);

        /*!< Selecionando idioma */
        data.put("plant_language", "en");

        /*!< Adicionando detalhes */
        JSONArray plantDetails = new JSONArray()
                .put("common_names")
//                .put("url")
                .put("name_authority")
                .put("wiki_description")
                .put("taxonomy")
                .put("synonyms");
        data.put("plant_details", plantDetails);

        /*!< Adicionando imagem */
        JSONArray images = new JSONArray()
                .put(imagemPlantaBase64);
        data.put("images", images);

        return data;
    }

    private String chamarAPI(JSONObject data) throws IOException {
        StringBuilder resposta = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        /*!< Enviando corpo da requisicao (objeto JSON) */
        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes());
        os.close();

        con.connect();

        /*!< Lendo resposta da requisicao */
        Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0;)
            resposta.append((char) c);

        con.disconnect();

        return resposta.toString();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resposta = new String();

        try {
            JSONObject data = criarMensagem();
//            resposta = chamarAPI(data);
            resposta = "{\"id\":6706408,\"custom_id\":null,\"meta_data\":{\"latitude\":null,\"longitude\":null,\"datetime\":\"2020-10-16\"},\"uploaded_datetime\":1602815995.36803,\"finished_datetime\":1602815996.502634,\"images\":[{\"file_name\":\"dd0ebed9c2ca411a981a36a525142f41.jpg\",\"url\":\"https://plant.id/media/images/dd0ebed9c2ca411a981a36a525142f41.jpg\"}],\"suggestions\":[{\"id\":48928809,\"plant_name\":\"Nephrolepis exaltata\",\"plant_details\":{\"scientific_name\":\"Nephrolepis exaltata\",\"structured_name\":{\"genus\":\"nephrolepis\",\"species\":\"exaltata\"},\"common_names\":[\"Sword fern\",\"Boston fern\",\"Boston swordfern\",\"Wild boston fern\",\"Boston blue bell fern\",\"Tuber ladder fern\",\"Fishbone fern\"],\"url\":\"http://en.wikipedia.org/wiki/Nephrolepis_exaltata\",\"name_authority\":\"Nephrolepis exaltata (L.) Schott\",\"wiki_description\":{\"value\":\"Nephrolepis exaltata, known as the sword fern or  Boston fern, is a species of fern in the family Lomariopsidaceae (sometimes treated in the families Davalliaceae or Oleandraceae, or in its own family, Nephrolepidaceae) native to tropical regions throughout the world. An evergreen perennial herbaceous plant, it can reach as high as 40–90 centimetres (16–35 in), and in extreme cases up to 1.5 metres (4 ft 11 in). It is also known as the Boston swordfern, wild Boston fern, Boston Fern, Boston Blue Bell Fern, tuber ladder fern, or fishbone fern.\",\"citation\":\"http://en.wikipedia.org/wiki/Nephrolepis_exaltata\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Nephrolepidaceae\",\"genus\":\"Nephrolepis\"},\"synonyms\":[\"Aspidium exaltatum\",\"Hypopeltis exaltata\",\"Nephrodium exaltatum\",\"Polypodium exaltatum\"]},\"probability\":0.5862011962903615,\"confirmed\":false,\"similar_images\":[{\"id\":\"99724061e50fb0c94bca3bf4715090cb\",\"similarity\":0.8429811715157796,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis exaltata/99724061e50fb0c94bca3bf4715090cb.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis exaltata/99724061e50fb0c94bca3bf4715090cb.small.jpg\"},{\"id\":\"7cf2a755ad617b3288d1502694554bfe\",\"similarity\":0.8409090674098579,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis exaltata/7cf2a755ad617b3288d1502694554bfe.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis exaltata/7cf2a755ad617b3288d1502694554bfe.small.jpg\"}]},{\"id\":48928810,\"plant_name\":\"Asplenium platyneuron\",\"plant_details\":{\"scientific_name\":\"Asplenium platyneuron\",\"structured_name\":{\"genus\":\"asplenium\",\"species\":\"platyneuron\"},\"common_names\":[\"Ebony spleenwort\",\"Brownstem spleenwort\"],\"url\":\"http://en.wikipedia.org/wiki/Asplenium_platyneuron\",\"name_authority\":\"Asplenium platyneuron (L.) Britton, Sterns & Poggenb.\",\"wiki_description\":{\"value\":\"Asplenium platyneuron (syn. Asplenium ebeneum), commonly known as ebony spleenwort or brownstem spleenwort, is a fern native to North America east of the Rocky Mountains and to South Africa. It takes its common name from its dark, reddish-brown, glossy stipe and rachis (leaf stalk and midrib), which support a once-divided, pinnate leaf. The fertile fronds, which die off in the winter, are darker green and stand upright, while the sterile fronds are evergreen and lie flat on the ground. An auricle at the base of each pinna points towards the tip of the frond. The dimorphic fronds and alternate, rather than opposite, pinnae distinguish it from the similar black-stemmed spleenwort.\\nThe species was first described in 1753 by Linnaeus as Acrostichum platyneuros, although Linnaeus' type drew on material from several other species as well. It was more commonly called Asplenium ebeneum, a name published by William Aiton in 1789, until the rediscovery and revival of the Linnaean epithet in the late Nineteenth Century. A number of forms and varieties of the species have been described, but few are recognized today; in particular, larger and more fertile specimens, those with more or less toothed leaves, and those with proliferating buds are considered to fall within the natural range of variation of the species, and do not require taxonomic distinction. A. platyneuron f. hortonae, a sterile form with the pinnae cut to toothed pinnules, and f. furcatum, with forking fronds, are still recognized.\\nThe formation of proliferating buds is one of several unusual adaptations for reproduction in this species. The buds form near the base of the stipe, and when covered with soil, can grow into new individuals as the frond that bore them dies. Ebony spleenwort is also well-adapted to propagate by spores: the upright sterile fronds help the spores enter the airstream for long-distance dispersal, and a low genetic load allows spores that have grown into a gametophyte to self-fertilize with a high degree of success. This dispersal ability seems to have helped the species spread rapidly in the Great Lakes region in the late 20th century. Long-distance dispersal may also explain its naturalized appearance in South Afric, and the existence of an isolated population found in Slovakia in 2009, its first known occurrence in Europe.\\nEbony spleenwort has broad habitat preferences, growing both on rocks like many other North American spleenworts and in a variety of soils. Unlike many other spleenworts, it is not particularly sensitive to soil pH. It hybridizes with several other spleenworts, particularly mountain spleenwort and walking fern; these species, their sterile hybrid offspring, fertile allotetraploid hybrids, and backcrosses between allotetraploids and the parents are collectively known as the \\\"Appalachian Asplenium complex\\\". Two hybrids between A. platyneuron and spleenworts outside of this complex are also known.\",\"citation\":\"http://en.wikipedia.org/wiki/Asplenium_platyneuron\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Aspleniaceae\",\"genus\":\"Asplenium\"},\"synonyms\":[]},\"probability\":0.1085391288653364,\"confirmed\":false,\"similar_images\":[{\"id\":\"faa8537d806b552e7b9f0801a685b34a\",\"similarity\":0.9105702704148713,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Asplenium platyneuron/faa8537d806b552e7b9f0801a685b34a.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Asplenium platyneuron/faa8537d806b552e7b9f0801a685b34a.small.jpg\"},{\"id\":\"b82a5a6f01768989608e5fa96c209117\",\"similarity\":0.9005346020344578,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Asplenium platyneuron/b82a5a6f01768989608e5fa96c209117.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Asplenium platyneuron/b82a5a6f01768989608e5fa96c209117.small.jpg\"}]},{\"id\":48928811,\"plant_name\":\"Polystichum acrostichoides\",\"plant_details\":{\"scientific_name\":\"Polystichum acrostichoides\",\"structured_name\":{\"genus\":\"polystichum\",\"species\":\"acrostichoides\"},\"common_names\":[\"Christmas fern\"],\"url\":\"http://en.wikipedia.org/wiki/Polystichum_acrostichoides\",\"name_authority\":\"Polystichum acrostichoides (Michx.) Schott\",\"wiki_description\":{\"value\":\"Polystichum acrostichoides, commonly denominated Christmas fern, is a perennial, evergreen fern native to eastern North America, from Nova Scotia west to Minnesota and south to Florida and eastern Texas. It is one of the most common ferns in eastern North America, being found in moist and shady habitats in woodlands, stream banks and rocky slopes. The common name derives from the evergreen fronds, which are often still green at Christmas.\",\"citation\":\"http://en.wikipedia.org/wiki/Polystichum_acrostichoides\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Dryopteridaceae\",\"genus\":\"Polystichum\"},\"synonyms\":[\"Nephrodium acrostichoides\"]},\"probability\":0.05649695294415723,\"confirmed\":false,\"similar_images\":[{\"id\":\"f5cbecdd4bb66c04b81ceb1500be5676\",\"similarity\":0.8972977836454159,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum acrostichoides/f5cbecdd4bb66c04b81ceb1500be5676.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum acrostichoides/f5cbecdd4bb66c04b81ceb1500be5676.small.jpg\"},{\"id\":\"f17b80ced79e77441fbc89a4e3aa6f7d\",\"similarity\":0.8939120181601353,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum acrostichoides/f17b80ced79e77441fbc89a4e3aa6f7d.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum acrostichoides/f17b80ced79e77441fbc89a4e3aa6f7d.small.jpg\"}]},{\"id\":48928812,\"plant_name\":\"Polypodium vulgare\",\"plant_details\":{\"scientific_name\":\"Polypodium virginianum\",\"structured_name\":{\"genus\":\"polypodium\",\"species\":\"virginianum\"},\"common_names\":[\"Rock polypody\",\"Rock cap fern\",\"Common polypody\"],\"url\":\"http://en.wikipedia.org/wiki/Polypodium_virginianum\",\"name_authority\":\"Polypodium virginianum L.\",\"wiki_description\":{\"value\":\"Polypodium virginianum, commonly known as rock polypody, rock cap fern, or common polypody, is a small evergreen species of fern native to the Eastern United States and Canada. It generally grows on rocks and occasionally on tree roots in nature.\",\"citation\":\"http://en.wikipedia.org/wiki/Polypodium_virginianum\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Polypodiaceae\",\"genus\":\"Polypodium\"},\"synonyms\":[\"Polypodium vulgare\",\"Polypodium vulgare var. virginianum\",\"Polypodium sibiricum\",\"Polypodium vinlandicum\",\"Polypodium vulgare ssp. virginianum\",\"Polypodium vulgare americanum\",\"Polypodium vulgare var. virginianum\"]},\"probability\":0.02321059330397899,\"confirmed\":false,\"similar_images\":[{\"id\":\"3194e92cd76454225bfddf762e63a711\",\"similarity\":0.8465353303466724,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polypodium vulgare/3194e92cd76454225bfddf762e63a711.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polypodium vulgare/3194e92cd76454225bfddf762e63a711.small.jpg\"},{\"id\":\"6f3bb1f3e747996a6d10fb2db6b34e02\",\"similarity\":0.8425996685758326,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polypodium vulgare/6f3bb1f3e747996a6d10fb2db6b34e02.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polypodium vulgare/6f3bb1f3e747996a6d10fb2db6b34e02.small.jpg\"}]},{\"id\":48928813,\"plant_name\":\"Nephrolepis cordifolia\",\"plant_details\":{\"scientific_name\":\"Nephrolepis cordifolia\",\"structured_name\":{\"genus\":\"nephrolepis\",\"species\":\"cordifolia\"},\"common_names\":[\"Fishbone fern\",\"Tuberous sword fern\"],\"url\":\"http://en.wikipedia.org/wiki/Nephrolepis_cordifolia\",\"name_authority\":\"Nephrolepis cordifolia (L.) C.Presl\",\"wiki_description\":{\"value\":\"Nephrolepis cordifolia, is a fern native to northern Australia and Asia. It has many common names including fishbone fern,tuberous sword fern,tuber ladder fern, erect sword fern, narrow sword fern and ladder fern, and herringbone fern. It is indigenous to the Hawaiian Islands where it is known as kupukupu, okupukupu or ni'ani'au  It is similar to the related fern Nephrolepis exaltata.\",\"citation\":\"http://en.wikipedia.org/wiki/Nephrolepis_cordifolia\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Nephrolepidaceae\",\"genus\":\"Nephrolepis\"},\"synonyms\":[]},\"probability\":0.017390579145028565,\"confirmed\":false,\"similar_images\":[{\"id\":\"85ef58dada32dcb1f2c8855c0eba1704\",\"similarity\":0.8405928180322518,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis cordifolia/85ef58dada32dcb1f2c8855c0eba1704.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis cordifolia/85ef58dada32dcb1f2c8855c0eba1704.small.jpg\"},{\"id\":\"306c3c9df77310a67c5f266aa07f4ec0\",\"similarity\":0.8052528483009538,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis cordifolia/306c3c9df77310a67c5f266aa07f4ec0.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Nephrolepis cordifolia/306c3c9df77310a67c5f266aa07f4ec0.small.jpg\"}]},{\"id\":48928814,\"plant_name\":\"Polystichum imbricans\",\"plant_details\":{\"scientific_name\":\"Polystichum imbricans\",\"structured_name\":{\"genus\":\"polystichum\",\"species\":\"imbricans\"},\"common_names\":[\"Narrowleaf swordfern\",\"Imbricate sword fern\"],\"url\":\"http://en.wikipedia.org/wiki/Polystichum_imbricans\",\"name_authority\":\"Polystichum imbricans (D.C.Eaton) D.H.Wagner\",\"wiki_description\":{\"value\":\"Polystichum imbricans is a species of fern known by the common names narrowleaf swordfern and imbricate sword fern. It is native to western North America from British Columbia to southern California, where it grows in rocky habitat in coastal and inland mountain ranges and foothills. This fern produces several erect linear or lance-shaped leaves up to 80 centimeters long. Each leaf is made up of many narrow, overlapping, sometimes twisting leaflets each 2 to 4 centimeters long. The leaflets have toothed edges. This fern readily forms hybrids, some of which are fertile and are considered separate species, such as Polystichum californicum, its hybrid with P. dudleyi.\",\"citation\":\"http://en.wikipedia.org/wiki/Polystichum_imbricans\",\"license_name\":\"CC BY-SA 3.0\",\"license_url\":\"https://creativecommons.org/licenses/by-sa/3.0/\"},\"taxonomy\":{\"kingdom\":\"Plantae\",\"phylum\":\"Tracheophyta\",\"class\":\"Polypodiopsida\",\"order\":\"Polypodiales\",\"family\":\"Dryopteridaceae\",\"genus\":\"Polystichum\"},\"synonyms\":[\"Aspidium munitum var. imbricans\",\"Polystichum munitum ssp. imbricans\",\"Polystichum munitum var. imbricans\"]},\"probability\":0.012082968896709307,\"confirmed\":false,\"similar_images\":[{\"id\":\"d15fb1b5dc9e183415e3645de6969aff\",\"similarity\":0.8591678282183157,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum imbricans/d15fb1b5dc9e183415e3645de6969aff.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum imbricans/d15fb1b5dc9e183415e3645de6969aff.small.jpg\"},{\"id\":\"7311e3d4c0ded27cfa39af3a0bc522f8\",\"similarity\":0.8418074689971786,\"url\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum imbricans/7311e3d4c0ded27cfa39af3a0bc522f8.jpg\",\"url_small\":\"https://storage.googleapis.com/plant_id_images/similar_images/2019_05/images/Polystichum imbricans/7311e3d4c0ded27cfa39af3a0bc522f8.small.jpg\"}]}],\"modifiers\":[\"crops_fast\",\"similar_images\"],\"secret\":\"dXV57Fo9hNek6Vd\",\"fail_cause\":null,\"countable\":true,\"feedback\":null}";
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resposta;
    }


}

