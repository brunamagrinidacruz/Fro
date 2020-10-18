package com.example.fro;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* Funcao responsvel por gerenciar o mock de dados sobre plantas
* */
public class BancoDePlantas {

    private Hashtable<String, Planta> bancoDePlantas = new Hashtable<String, Planta>();;

    private List<String> nomesPlantas = new ArrayList<String>(); /*!< Lista com os nomes apresentaveis das plantas (para o Spinner) */
    private List<String> urlPlantas = new ArrayList<String>(); /*!< Lista com os url das plantas (para o ImageView) */
    private List<String> keysPlantas = new ArrayList<>(); /*!< Lista de chaves para recuperação da planta pela tela DescricaoPlanta */

    public BancoDePlantas()  {
        /*!< Mock */

        Planta samambaia = new Planta();
        samambaia.setNome("Samambaia");
        samambaia.setFrequenciaDeRegamento("A planta precisa de água, mas não deve ficar encharcada. O indicado é regar um pouco todos os dias. Mantenha o vaso úmido. Para não ter erro, coloque o dedo na terra: se ele sair sujo, não precisa molhar. Evite regar a folhagem.");
        samambaia.setLocalAdequadoParaPlantio("Procure um lugar de meia sombra, onde não bata o sol forte do meio-dia. Tome cuidado para escolher um canto longe de correntes de vento, que desidratam a planta e fazem as folhas caírem.");
        samambaia.setFertilizantesRecomendados("Para suas plantas ficarem tão lindas quanto na floricultura, misture 2 col. (sopa) de torta de mamona e 1 col. (sopa) de farinha de osso e espalhe na terra, a cada 40 dias. Você também pode borrifar as folhas num mês com NPK 20-20-20 e, no outro, com NPK 15-05-30, seguindo as orientações de diluição da embalagem.");
        samambaia.setPoda("O ideal é fazer uma vez por ano. Já os galhos secos ou mortes e partes doentes da planta podem ser removido antes.");
        samambaia.setTempoDeVida("Cerca de 2 anos.");
        samambaia.setPreco("Aproximadamente R$40,00.");
        samambaia.setUrlImagemPadrao("samambaia");
        bancoDePlantas.put("sword fern", samambaia);
        bancoDePlantas.put("boston fern", samambaia);
        bancoDePlantas.put("boston swordfern", samambaia);
        bancoDePlantas.put("wild boston fern", samambaia);
        bancoDePlantas.put("boston blue bell fern", samambaia);
        bancoDePlantas.put("tuber ladder fern", samambaia);
        bancoDePlantas.put("fishbone fern", samambaia);

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
        lancaSaoJorge.setUrlImagemPadrao("lanca_sao_jorge");
        bancoDePlantas.put("sword barbara saint", lancaSaoJorge);
        bancoDePlantas.put("snake plant", lancaSaoJorge);
        bancoDePlantas.put("dracaena trifasciata", lancaSaoJorge);
        bancoDePlantas.put("saint george's sword", lancaSaoJorge);
        bancoDePlantas.put("mother-in-law's tongue", lancaSaoJorge);
        bancoDePlantas.put("viper's bowstring hemp", lancaSaoJorge);

        Planta zamioculca = new Planta();
        zamioculca.setNome("Zamioculca");
        zamioculca.setComoPlantar ("São necessários vasos com tamanho adequado para que a planta possa se desenvolver sem replantio. Precisaremos de uma manta filtrante para colocar no fundo do vaso, depois uma camada de 1/4 da altura do vaso de argila expandida para auxiliar na drenagem e a seguir o solo para plantio, que deve ter as seguintes proporções: 1 litro de terra vegetal rica em matéria orgânica, comprada em gardens centers, para cada 1 litro de terra comum, 1/2 litro de areia e 1/2 litro de húmus de minhoca. Com a ajuda de uma pá coloque a terra preparada dentro do vaso, coloque a planta e preencha com o restante da terra. O ideal é se fazer o replantio dos vasos de plantas a cada 4 anos para o desmembramento da touceira, originando novas mudas e fazendo a renovação total da terra.");
        zamioculca.setFrequenciaDeRegamento("Por ter necessidade baixa de água, as regas das Zamioculcas devem acontecer de 1 a 2 vezes por semana, de forma que o solo esteja sempre úmido, porém não encharcado. O ideal é ficar atento para que a rega aconteça antes que o solo fique totalmente seco.");
        zamioculca.setLocalAdequadoParaPlantio("Não atura exposição direta ao sol; coloque em local com iluminação indireta. Como se trata de uma espécie tóxica, não é recomendável utilizar Zamioculcas em locais onde há a presença de animais que têm o costume de cavar as plantas ou mesmo de comê-las.");
        zamioculca.setFertilizantesRecomendados("Se o substrato for rico em matéria orgânica não há necessidade de adubo químico. Caso queira, poderá usar NPK 10 -10 - 10 ou farinha de osso.");
        zamioculca.setPoda("Não há necessidade de podas drásticas. Faça somente uma poda de manutenção, retirando as folhas amarelas. Caso cresça muito, poderá ser feita uma divisão da planta. Reserve uma parte para o vaso e caso não queira fazer uso das outras partes, faça mudas e presenteie alguém.");
        zamioculca.setPragasComuns("As pragas são um dos fatores de causa para manchas nas folhas das Zamioculcas. Neste caso, as manchas podem ser causadas por pulgões, ácaros e mais comumente, por fungos, o que pode ser o caso das manchas esbranquiçadas na folhas da Zamioculcas. Uma das recomendações seria aplicar um fungicida FWZ, via foliar, com um borrifador. Na sequência, avaliar os resultados após duas semanas e rever a composição do vaso, especialmente quanto à aplicação de resíduos orgânicos não compostados. Também é importante avaliar se a planta está recebendo água em excesso.");
        zamioculca.setAltura("Esta planta tem crescimento muito lento levando cerca de dois anos para atingir sua altura máxima: cerca de 1 metro.");
        zamioculca.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        zamioculca.setPreco("Preço em torno de R$ 90,00.");
        zamioculca.setOutrasInformacoes("Por ter semelhança com a espécie zâmia, a planta deu origem ao nome científico Zamioculca. Mas, ao longo dos anos, ela acabou recebendo outros nomes por conta das crenças populares e das linhas do Feng Shui que a relaciona ao sucesso financeiro. Esse motivo a tornou conhecida também como árvore de dinheiro, em relação principalmente ao seu tom esverdeado. Uma curiosidade relevante dessa planta é que ela também serve para atrair proteção e sorte, sendo altamente indicada para colocar na entrada dos espaços.");
        zamioculca.setUrlImagemPadrao("zamioculca");
        bancoDePlantas.put("zz plant", zamioculca);
        bancoDePlantas.put("zanzibar gem", zamioculca);
        bancoDePlantas.put("zamioculcas", zamioculca);
        bancoDePlantas.put("zuzu plant", zamioculca);
        bancoDePlantas.put("eternity plant", zamioculca);
        bancoDePlantas.put("emerald palm", zamioculca);

        Planta palmeiraRafia = new Planta();
        palmeiraRafia.setNome("Palmeira Ráfia");
        palmeiraRafia.setComoPlantar ("Primeiramente, escolha um vaso grande, uma vez que a planta cresce rápido. Forre o fundo do vaso com pedriscos e areia para garantir a drenagem da água. Em seguida, acrescente um composto de terra e substrato orgânico com adubo NPK. Faça um buraco e plante a muda. Complete com substrato e firme a planta no vaso com a ponta dos dedos. Em seguida, regue-a. ao notar os pequenos frutos na sua planta, colha-os e seme-os em um vaso com substrato úmido. Mantenha o vaso longe do sol até as sementes germinarem, quando isso acontecer transplante as pequenas mudas para um saco de cultivo até que cresçam o suficiente para serem plantadas em um vaso definitivo.");
        palmeiraRafia.setFrequenciaDeRegamento("Se atente à umidade do vaso antes de começar a irrigação. De modo geral, ela deve ser regada a cada 15 dias nos dias mais quentes. No inverno, uma vez por mês é suficiente.");
        palmeiraRafia.setLocalAdequadoParaPlantio("Escolha um vaso adequado, uma vez que a planta cresce bastante. A Palmeira Ráfia prefere locais bem iluminadas mas distante do sol. Ambientes com ar condicionado podem prejudicar a planta, queimando a ponta das folhas; caso ocorra, borrife água para ajudar.");
        palmeiraRafia.setFertilizantesRecomendados("A adubação da planta deve ser feita com adubo do tipo NPK (10-10-10) uma vez por ano com um pouco de húmus de minhoca.");
        palmeiraRafia.setPoda("Não há necessidade de poda. Mantenha a planta limpa a saudável retirando as folhas velhas e amareladas.");
        palmeiraRafia.setPragasComuns("Doença causada por fungos da parte aérea Colletotrichum sp, ataca principalmente as folhas causando manchas pardas. Lesões nas folhas por Pestalotiopsis sp, fungo que desenvolve-se sobre as folhdas e causa pequenas manchas quase imperceptíveis. ");
        palmeiraRafia.setAltura("Pode atingir cerca de 4 metros quando adulta.");
        palmeiraRafia.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        palmeiraRafia.setPreco("Preço médio em torno de R$10. Preço encarecido apenas pelo tipo de vaso.");
        palmeiraRafia.setOutrasInformacoes("É uma linda planta originária da China que chama a atenção pelos troncos finos, pelas fibras espessas e pelas folhas verdes grandes e suntuosas. O uso mais comum da palmeira ráfia é como adorno para decoração de interiores, sobretudo para compor cômodos e ambientes com grandes espaços.");
        palmeiraRafia.setUrlImagemPadrao("palmeira_rafia");
        bancoDePlantas.put("parlour palm", palmeiraRafia);
        bancoDePlantas.put("neanthe bella palm", palmeiraRafia);
        bancoDePlantas.put("golden butterfly palm", palmeiraRafia);
        bancoDePlantas.put("areca palm", palmeiraRafia);
        bancoDePlantas.put("golden cane palm", palmeiraRafia);

        Planta paudAgua = new Planta();
        paudAgua.setNome("Pau D'Água");
        paudAgua.setComoPlantar ("Escolha vasos de tamanho médio a grande, com furos para drenagem. Proteger os furos com brita ou manta geotêxtil. Por cima, colocar areia úmida. Usar adubo NPK 10-10-10, cerca de 100 gramas por muda misturado ao composto orgânico. Regar após o plantio.");
        paudAgua.setFrequenciaDeRegamento("O pau d'água não precisa de muita água. Para mantê-la úmida, você pode pulverizá-la com água entre duas e três vezes por semana ou passar um pano úmido pelas suas folhas de vez em quando para limpar. Se observar que as pontas das suas folhas começam a secar, estará pedindo urgentemente uma dose de umidade. Em épocas de clima muito seco, também é recomendável pulverizar as folhas borrifando um pouco de água. Precisa de umidade mas não muita irrigação.");
        paudAgua.setLocalAdequadoParaPlantio("A luz solar não deve bater de forma direta, para não queimar as folhas. Deve ser colocado em local iluminado para evitar escurecimento da planta. O mais adequado é mantê-la numa temperatura entre 20ºC e 25ºC.");
        paudAgua.setFertilizantesRecomendados("Adubo NPK 10-10-10 na seguinte dosagem: 1 litro de água mais 1 colher de sopa do produto. Dissolva e coloque no substrato.");
        paudAgua.setPoda("A poda doa pau d'água costuma ser feita por uma questão estética e para evitar o seu crescimento exagerado de acordo com o ambiente. Para podar o pau d'água, com uma ferramenta esterilizada, incline-a a 25 graus e corte primeiro a parte superior, como observará, deste corte nascerão dois brotos laterais. Coloque na água um dos brotos e quando observar que dele nascem raízes, poderá plantá-lo em um vaso novo.");
        paudAgua.setPragasComuns("Cochonilhas: são insetos encontrados nas folhas e caules da planta. Elas causam deformação e perda de cor dessas partes da planta. Para se livrar delas, use uma escova úmida com água e sabão ou algum inseticida específico contra. Pulgões: são parasitas que se alimentam de novas folhas e botões de flores. Ele enfraquece a planta favorecendo o aparecimento de fungos e a perda de cor. Para eliminá-los, coloque armadilhas adesivas e cromáticas amarelas, use inseticidas específicos. Septoria: este é um fungo que gera manchas cinzentas nas folhas da planta. Para eliminar este fungo, use um fungicida sistêmico.");
        paudAgua.setAltura("Pode atingir cerca de 3-6 metros quando adulta.");
        paudAgua.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        paudAgua.setPreco("Preço em torno de R$ 40,00.");
        paudAgua.setOutrasInformacoes("Estudos da NASA comprovaram que ela contribui para eliminar produtos como formaldeído, xileno e tolueno do ar.");
        paudAgua.setUrlImagemPadrao("pau_de_agua");
        bancoDePlantas.put("fragrant dracaena", paudAgua);
        bancoDePlantas.put("cornstalk dracaena", paudAgua);
        bancoDePlantas.put("happy plant", paudAgua);

        Planta palmeiraLeque = new Planta();
        palmeiraLeque.setNome("Palmeira Leque");
        palmeiraLeque.setComoPlantar ("Fazer mistura de substratos com composto orgânico e elementos particulados como pó de coco e areia grossa. Adicionar cerca de 200 gramas de adubo NPK formulação 10-10-10, misturando bem. Para plantas de cultivo em interiores não devemos usar adubo animal para evitar odores. Proteger o fundo com geomanta, colocar brita ou cascalho no fundo e um pouco de areia úmida. Este procedimento facilita a saída da água de rega. Colocar parte do substrato no fundo, colocar o torrão e preencher as laterais. Apertar de leve para compactar e manter a muda no lugar.");
        palmeiraLeque.setFrequenciaDeRegamento("Regar toda vez que sentir o substrato levemente seco. No inverno diminuir as regas.");
        palmeiraLeque.setLocalAdequadoParaPlantio("A palmeira de leque é uma planta de crescimento lento e que precisa de meia sombra para continuar crescendo saudável. O jardim de inverno, varandas e vasos são lugares excelentes para usar a palmeira de leque, mas lembre-se de sempre a posicionar em lugares iluminados. No entanto, a planta não pode ficar em ambientes com ar condicionado e nem com muito vento – isso prejudica o crescimento da planta palmeira de leque.");
        palmeiraLeque.setFertilizantesRecomendados("Adubações complementares tanto em canteiros como em vasos são com adubo NPK 10-10-10. Em canteiro espalhar o 100 gramas do adubo e incorporar ao substrato regando a seguir. Em vasos diluir uma colher de sopa em um litro de água e regar o substrato, que já deverá ter alguma umidade. A propagação desta palmeira é feita por sementes.");
        palmeiraLeque.setPoda("A manutenção da palmeira-leque é super simples. Consiste na remoção das folhas velhas e secas.");
        palmeiraLeque.setPragasComuns("A maioria das pragas, geralmente conchonilhas ou pulgões, se escondem na parte de baixo de suas folhas. Caso apareçam, limpe cada folha com um pano limpo e álcool e depois pulverize a cada semana com um fumo de role, até as pragas desaparecerem.");
        palmeiraLeque.setAltura("A espécie de planta palmeira leque é considerada de pequeno porte, pois ela dificilmente ultrapassa os 3 metros de altura.");
        palmeiraLeque.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        palmeiraLeque.setPreco("Preço em torno de R$50 a R$90.");
        palmeiraLeque.setOutrasInformacoes("Ela chama atenção, no entanto, por suas folhas tão singulares. Elas são grandes, redondas a triangulares, coriáceas, de cor verde-brilhante, plissadas e com margem denteada. Estas folhas são sustentadas por pecíolos fortes, longos e espinhentos. Apenas os indivíduos já com tamanho considerável de tronco florescem e frutificam. As flores são de cor creme, diminutas e surgem em inflorescências ramificadas que despontam em longos cachos. Os frutos são vermelho-alaranjados, esféricos e bastante decorativos. Ocorre ainda uma forma variegada da planta com belas folhas listradas de branco.");
        palmeiraLeque.setUrlImagemPadrao("palmeira_leque");
        bancoDePlantas.put("mexican fan palm", palmeiraLeque);
        bancoDePlantas.put("mexican washingtonia", palmeiraLeque);
        bancoDePlantas.put("chinese fan palm", palmeiraLeque);
        bancoDePlantas.put("fountain palm", palmeiraLeque);

        Planta anturio = new Planta();
        anturio.setNome("Anturio");
        anturio.setComoPlantar ("As sementes de antúrio ficam localizadas na espiga da planta e para serem plantadas precisam estar frescas e úmidas. Remova-as com cuidado da planta adulta e certifique-se de já ter por perto um vaso com substrato para plantá-las. Também é importante recriar o clima tropical em que a planta está habituada. Para isso, cubra o vaso com um pano (de cor clara) ou plástico filme, mantendo um espaço entre a terra e a cobertura. Molhe o substrato com frequência, mas sem encharcar o solo. Coloque o vaso em um local sombreado, mas que ainda assim receba luz indireta. A temperatura ideal para o plantio das sementes de antúrio é acima dos 24ºC. Caso more em uma região de clima frio, espere até o verão para plantar o seu antúrio. Depois do plantio aguarde cerca de 20 a 30 dias para que a germinação aconteça.");
        anturio.setFrequenciaDeRegamento("A rega, assim como a luz, deve ser constante, mas sem exageros. Lembre-se que antúrios apreciam umidade, no entanto, isso não significa que você precisa encharcar o solo. Faça regas regulares sempre que notar a terra seca. Nos meses de verão, as regas provavelmente serão mais frequentes. Outra dica é borrifar água nas folhas do antúrio. Faça isso semanalmente e você verá a planta cada vez mais brilhante e viçosa.");
        anturio.setLocalAdequadoParaPlantio("Por se tratar de uma espécie tropical, o antúrio aprecia calor, mas isso não quer dizer que ele adora ficar no sol, pelo contrário. A planta precisa de sombra e luminosidade indireta, ou seja, muita luz, mas sem exposição direta ao sol. As temperaturas abaixo dos 20ºC são prejudiciais para os antúrios. Em regiões de clima frio, o mais aconselhável é manter a espécie dentro de casa, protegida dos ventos e do ar frio. Uma boa dica é posicionar o vaso pertinho da janela. Mas nunca, em hipótese alguma, coloque o antúrio em um ambiente com ar condicionado.");
        anturio.setFertilizantesRecomendados("A cada três meses ofereça um reforço de vitaminas e nutrientes para o antúrio, usando adubos adequados. Você pode optar por adubos orgânicos e naturais ou por aqueles vendidos em lojas de jardinagem, o mais comum e utilizado para antúrios é o NPK 10-10-10.");
        anturio.setPoda("O antúrio não precisa de podas. Faça apenas o corte das folhas que estejam secas, mortas ou amareladas. Assim você evita contaminação nas folhas saudáveis.");
        anturio.setPragasComuns("As mais comuns são pulgões, ácaros, cochonilhas, lesmas, caracóis e lagartas. Na maior parte das vezes essas pragas podem ser combatidas com receitinhas caseiras simples, como a água com fumo e a água com sabão que devem ser pulverizadas sobre as folhas da planta. Outros sintomas de que o antúrio não está bem são as folhas amareladas. Isso tende a acontecer quando a planta está em um ambiente com pouca luz ou também quando o solo está muito molhado. Para resolver o problema, coloque a planta em um local mais iluminado e diminua a frequência das regas. Já quando as folhas ficam secas e quebradiças significa que o antúrio está sofrendo com falta de água. Resolva esse problema com regas frequentes e lembre-se também de borrifar água nas folhas, especialmente nos dias mais quentes. Entretanto quando notar manchas marrons nas folhas, saiba que o seu antúrio está sendo atacado por fungos. Isso pode acontecer quando a planta recebe mais água do que deveria e pouca iluminação. Para contornar o problema, remova as folhas doentes e aplique fungicidas específicos para plantas.");
        anturio.setAltura("O antúrio é uma planta baixa, atingindo de 30 cm até 1 m de altura.");
        anturio.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        anturio.setPreco("Preço em torno de R$30.");
        anturio.setOutrasInformacoes("O antúrio é uma planta tóxica, por isso mantenha-o longe de crianças e animais domésticos. Ao manipular a planta também é recomendado utilizar luvas para evitar irritações na pele. Existem no mundo nada mais, nada menos do que 600 espécies diferentes de antúrios. Aqui no Brasil, a mais conhecida é a vermelha, mas existem ainda antúrios com “flores” brancas, rosas, salmão e marrom.");
        anturio.setUrlImagemPadrao("anturio");
        bancoDePlantas.put("tail flower", anturio);
        bancoDePlantas.put("tailflower", anturio);
        bancoDePlantas.put("flamingo flower", anturio);
        bancoDePlantas.put("laceleaf", anturio);
        bancoDePlantas.put("anthurium", anturio);

        Planta jiboia = new Planta();
        jiboia.setNome("Jiboia");
        jiboia.setComoPlantar ("Para plantar a partir de uma já existente, siga os seguintes passos: corte a ponta de um ramo com cerca de 5 folhas pela diagonal. Coloque o ramo num recipiente com água num lugar que recebe luz indireta. Assim que as raízes estiverem aparentes, com cerca de 5 cm, mude a planta jiboia para um vaso com substrato úmido e rico em matéria orgânica. Pronto! Agora é só cuidar da sua planta para ela crescer linda!.");
        jiboia.setFrequenciaDeRegamento("Trepadeira tropical de fácil manutenção, a jiboia gosta de água e calor. Regue-a duas vezes por semana, aumentando o fornecimento de água no verão e diminuindo no inverno.");
        jiboia.setLocalAdequadoParaPlantio("A trepadeira Jiboia prefere locais com meia sombra e Sol pleno por conta da iluminação.");
        jiboia.setFertilizantesRecomendados("O solo deve ser rico em matéria orgânica: adicione composto ou húmus de minhoca a cada três meses, revolvendo bem a terra para misturar.");
        jiboia.setPoda("Aparar os ramos durante a primavera para retirar as folhas secas e amareladas da planta com ajuda da tesoura de poda.");
        jiboia.setPragasComuns("A mais comum é a infestação de cochonilha, por falta de adubação. Neste caso, deve-se eliminar as partes afetadas e usar calda de fumo, uma boa alternativa natural, ou pesticidas botânicos para tratar os efeitos do ataque.");
        jiboia.setAltura("Atinge de 1m a 18m no tamanho.");
        jiboia.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        jiboia.setPreco("Preço em torno de R$20.");
        jiboia.setOutrasInformacoes("a jiboia apresenta folhagens brilhantes e atrativas, e é considerada por muitos uma planta protetora e sagrada. Antes de aprender como cuidar da planta jiboia, fique atento: ela é tóxica e pode apresentar perigo às crianças e animais de estimação, caso seja ingerida. Por isso, deixe-a fora do alcance dos pets e dos pequenos.");
        jiboia.setUrlImagemPadrao("jiboia");
        bancoDePlantas.put("centipede tongavine", jiboia);
        bancoDePlantas.put("dragon-tail plant", jiboia);
        bancoDePlantas.put("tibatib", jiboia);
        bancoDePlantas.put("centipede tongavine", jiboia);

        Planta singonio = new Planta();
        singonio.setNome("Singônio");
        singonio.setComoPlantar ("Para plantar o singônio, prepare o solo com duas partes de composto orgânico, uma parte de areia e outra de terra comum, bem misturadas. Para reproduzir, basta retirar muda com pelo menos um par de folhas e cerca de 4 centímetros da ponta do ramo, mantendo as raízes em um recipiente com água. Tão logo as novas raízes surjam, ele já estará pronto para ganhar casa nova.");
        singonio.setFrequenciaDeRegamento("Em locais menos iluminados, deixe a terra secar um pouco antes de regar novamente. Em locais mais bem iluminados, tente manter a terra constantemente úmida. Evite manter o solo encharcado, pois a planta poderá apodrecer.");
        singonio.setLocalAdequadoParaPlantio("Ele cresce bem em temperaturas medianas, e umidade do ar acima dos 30%. A planta não tolera baixas temperaturas, nem geadas.");
        singonio.setFertilizantesRecomendados("A adubação de reposição pode ser feita na primavera quando reinicia seu crescimento,com adubo granulado fórmula NPK 10-10-10, dissolvendo uma colher de sopa em dois litros de água e colocar no substrato levemente umedecido.");
        singonio.setPoda("Faça apenas o corte das folhas que estejam secas, mortas ou amareladas. Assim você evita contaminação nas folhas saudáveis.");
        singonio.setPragasComuns("As mais comuns são ácaros, cochonilhas e lagartas. Na maior parte das vezes essas pragas podem ser combatidas com receitinhas caseiras simples, como a água com fumo e a água com sabão que devem ser pulverizadas sobre as folhas da planta.");
        singonio.setAltura("Planta de baixa estatura, pode chegar até 1 metro de comprimento.");
        singonio.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        singonio.setPreco("Preço em torno de R$20.");
        singonio.setOutrasInformacoes("Antes de aprender como cuidar da planta jiboia, fique atento: ela é tóxica e pode apresentar perigo às crianças e animais de estimação, caso seja ingerida. Por isso, deixe-a fora do alcance dos pets e dos pequenos.");
        singonio.setUrlImagemPadrao("singonio");
        bancoDePlantas.put("american evergreen", singonio);
        bancoDePlantas.put("arrowhead vine", singonio);
        bancoDePlantas.put("arrowhead plant", singonio);
        bancoDePlantas.put("arrowhead philodendron", singonio);
        bancoDePlantas.put("goosefoot", singonio);
        bancoDePlantas.put("african evergreen", singonio);

        Planta pacova = new Planta();
        pacova.setNome("Pacová");
        pacova.setComoPlantar ("O ideal é colocar a pacová em vaso feitos de xaxim ou jardineiras. A melhor maneira de como fazer muda de pacová é por meio de sementes ou separação das raízes. O ideal é que a separação seja feita com plantas mais velhas, que tenham o caule aparente. Separe uma folha com raiz da pacová com ajuda de uma tesoura de poda e plante no vaso adequado.");
        pacova.setFrequenciaDeRegamento("A planta pacová gosta de rega moderada: uma vez por semana é suficiente. Porém, tenha cuidado para não encharcar o solo ao usar o regador. O ideal é deixar o solo úmido.");
        pacova.setLocalAdequadoParaPlantio("A pacová planta prefere clima quente e úmido, portanto é recomendado posicioná-la em ambientes com luz indireta ou à meia sombra, com incidência de sol ameno no início ou fim do dia.");
        pacova.setFertilizantesRecomendados("Cultive a pacová em solo fértil, e realize a adução constantemente com fertilizante orgânico.");
        pacova.setPoda("Podas de limpeza, removendo folhas secas e doentes.");
        pacova.setPragasComuns("Pode acontecer de aparecer pulgões e cochonilhas no pacová em vaso. Para manter as plantas livre de pragas, esfregue as folhas com uma solução de água e sabão de coco.");
        pacova.setAltura("A Pacova é uma espécie vegetal que possuem uma altura média de 1 metro.");
        pacova.setTempoDeVida("Possui ciclo de vida perene, ou seja, é longo (pode durar mais de dois anos) e suas folhas não caem.");
        pacova.setPreco("Preço em torno de R$30,00.");
        pacova.setOutrasInformacoes("A planta pacová (nome científico Philodendron martianum), também conhecida como filodendro martiniano, babosa-de-árvore ou babosa-de-pau é uma espécie nativa do Brasil, mais especificamente da Mata Atlântica.");
        pacova.setUrlImagemPadrao("pacova");
        bancoDePlantas.put("natal wild banana", pacova);
        bancoDePlantas.put("giant white bird of paradise", pacova);
        bancoDePlantas.put("wild banana", pacova);

        Planta cacto = new Planta();
        cacto.setNome("Cacto Orelha-de-Coelho");
        cacto.setComoPlantar ("Para plantar, separe os seguintes itens:  substrato, cascalho ou areia e adubo com nitrogênio e fósforo. Retire o broto com cuidado e, usando luvas de proteção, coloque-o na terra e aperte o substrato, apenas o suficiente para que ele fique em pé. Adicione uma camada de cascalho ou areia sobre a terra ao redor do cacto. Coloque o vaso em um local quente, que receba sol de quatro a seis horas por dia, mas que não seja abafado.");
        cacto.setFrequenciaDeRegamento("Os mini cactos não podem ser regados constantemente, por armazenarem água por bastante tempo. Você pode regá-lo uma vez por semana, durante o verão, uma colher de sopa de água. Durante o inverno, essa quantidade pode ser reduzida para uma vez ao mês. Se a região que você mora é muito úmida, é importante usar vasos de cerâmica, pois eles ajudam a manter a planta longe da umidade.");
        cacto.setLocalAdequadoParaPlantio("Os cactos gostam de lugares ensolarados. Como são plantas de deserto, apenas a claridade intensa não é suficiente. O ideal são pelo menos 2 a 3 dias de sol por semana.");
        cacto.setAltura("O tamanho pode variar.");
        cacto.setPreco("Você encontra cactos a partir de R$3,00.");
        cacto.setFertilizantesRecomendados("Fertilize os cultivos com adubo diluído em água, preferencialmente durante a primavera e o verão. O Forth Cactos é um dos fertilizantes para cactos mais indicados para a etapa de adubação, garantindo plantas sadias, com floração abundante e colorida.");
        cacto.setPoda("Localize os ramos que serão cortados. A razão mais importante para podar um cacto é eles estarem doentes com fungos e bactérias, com podridão e partes brancas. Remova segmentos secos ou mortos.");
        cacto.setPragasComuns("Os piolhos de cobra são uma das pragas mais comuns que afetam os cactus, especialmente aqueles que estão diretamente plantados sobre o solo do jardim e não num vaso. Estes atacam diretamente as raízes dos cactus, que vão enfraquecendo lentamente sem razão aparente até morrer. Para os eliminar, será necessário um inseticida de solo que os ataque diretamente.  os ácaros também podem atacar os cactus e danificá-los, uma das possíveis evidências que denotam a presença desta praga são áreas avermelhadas na superfície dos nossos cactus. No caso de as detetar, deverá iniciar um tratamento anti-ácaros nas plantas afetadas.");
        cacto.setOutrasInformacoes("Todos os tipos de cactos dão flores. Algumas espécies só florescem depois de alcançarem os 80 anos de idade (elas podem viver por até 200 anos) ou os dois metros de altura. Depois que as flores aparecem pela primeira vez, elas voltam todos os anos, na mesma época. Outra das curiosidades sobre os cactos: eles se reproduzem tanto por sementes quanto por estacas!.");
        cacto.setUrlImagemPadrao("cacto");
        bancoDePlantas.put("bunny ears", cacto);
        bancoDePlantas.put("angel's-wings", cacto);
        bancoDePlantas.put("bunny ears cactus", cacto);
        bancoDePlantas.put("bunny cactus", cacto);
        bancoDePlantas.put("polka-dot cactus", cacto);
        bancoDePlantas.put("beavertail cactus", cacto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bancoDePlantas.forEach((key, planta) -> {
                if(!nomesPlantas.contains(planta.getNome())) {
                    nomesPlantas.add(planta.getNome());
                    urlPlantas.add(planta.getUrlImagemPadrao());
                    keysPlantas.add(key);
                }
            });
        } else {
            System.out.println("DEU RUIM D+++++++++++++++++");
        }

    }

    public Planta identificarPlanta(JSONArray nomesComuns) throws JSONException {
        String nomeComum;
        Planta planta = null;
        for(int i = 0; i < nomesComuns.length(); i++) {
            /*!< Remover acentos e colocar minusculo */
            nomeComum = (Normalizer.normalize(nomesComuns.getString(i), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")).toLowerCase();
            /*!< Procurando se o nome comum possui alguma planta relacionada no banco de plantas */
            planta = bancoDePlantas.get(nomeComum);
            if(planta != null) {
                planta.setId(nomeComum);
                break;
            }
        }
        return planta;
    }

    public List<String> getNomesPlantas() {
        return this.nomesPlantas;
    }

    public List<String> getUrlPlantas() {
        return this.urlPlantas;
    }

    public List<String> getKeysPlantas() {
        return this.keysPlantas;
    }

    public Planta getPlantaPorKey(String keyPlanta) {
        return bancoDePlantas.get(keyPlanta);
    }
}
