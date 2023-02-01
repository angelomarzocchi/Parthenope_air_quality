package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

/**
 * Enumerazione dei comuni di Napoli
 */
public enum Comuni {
    Napoli("Napoli", 40.8359336, 14.2487826),
    Giugliano_in_Campania("Giugliano in Campania", 40.9286009, 14.1971619),
    Torre_del_greco("Torre del greco", 40.7872187, 14.3680286),
    Pozzuoli("Pozzuoli", 40.8237447, 14.1216220),
    Casoria("Casoria", 40.9054455, 14.2900745),
    Castellamare_di_Stabia("Castellamare di stabia", 40.6960023, 14.4816744),
    Afragola("Afragola", 40.9210375, 14.3072168),
    Marano_di_Napoli("Marano di Napoli", 40.8981433, 14.1947816),
    Acerra("Acerra", 40.9517222, 14.3772037),
    Portici("Portici", 40.8140513, 14.3390187),
    Ercolano("Ercolano", 40.8060384, 14.3528566),
    Casalnuovo_di_Napoli("Casalnuovo di Napoli", 40.9103333, 14.3467407),
    San_Giorgio_a_Cremano("San Giorgio a Cremano", 40.8291700, 14.3341929),
    Torre_Annunziata("Torre Annunziata", 40.7507962, 14.4656481),
    Quarto("Quarto", 40.8778148, 14.1403148),
    Pomigliano_d_Arco("Pomigliano d'Arco", 40.9089074, 14.3872037),
    Melito_di_Napoli("Melito di Napoli", 40.9215740, 14.2309444),
    Caivano("Caivano", 40.9580562, 14.3080949),
    Somma_Vesuviana("Somma Vesuviana", 40.8717672, 14.4382437),
    Mugnano_di_Napoli("Mugnano di Napoli", 40.9123518, 14.2061111),
    Arzano("Arzano", 40.9154975, 14.2681516),
    Nola("Nola", 40.9258774, 14.5285639),
    Sant_Antimo("Sant'Antimo", 40.9429240, 14.2362070),
    Villaricca("Villaricca", 40.9226666, 14.1907962),
    San_Giuseppe_Vesuviano("San Giuseppe Vesuviano", 40.8367280, 14.5032940),
    Frattamaggiore("Frattamaggiore", 40.9403148, 14.2750185),
    Marigliano("Marigliano", 40.9241870, 14.4542130),
    Gragnano("Gragnano", 40.6925103, 14.5137891),
    Boscoreale("Boscoreale", 40.7754232, 14.4751592),
    Sant_Anastasia("Sant'Anastasia", 40.8679814, 14.4057962),
    Bacoli("Bacoli", 40.8012999, 14.0797960),
    Qualiano("Qualiano", 40.9185050, 14.1531210),
    Pompei("Pompei", 40.7491819, 14.5007385),
    Volla("Volla", 40.8795555, 14.3439259),
    Ottaviano("Ottaviano", 40.8499907, 14.4829454),
    Cardito("Cardito", 40.9455890, 40.9455890),
    Poggiomarino("Poggiomarino", 40.8042407, 14.5400185),
    Vico_Equense("Vico Equense", 40.6623521, 14.4263826),
    Ischia("Ischia", 40.7266616, 13.9089620),
    Sant_Antonio_Abate("Sant'Antonio Abate", 40.7228050, 14.5429350),
    Terzigno("Terzigno", 40.8082176, 14.5026808),
    Casavatore("Casavatore", 40.9003333, 14.2775000),
    Cercola("Cercola", 40.8576666, 14.3587777),
    Grumo_Nevano("Grumo Nevano", 40.9370555, 14.2607962),
    Forio("Forio", 40.7373941, 13.8592074),
    Sorrento("Sorrento", 40.6263211, 14.3757383),
    Brusciano("Brusciano", 40.9242222, 14.4248518),
    Frattaminore("Frattaminore", 40.9565740, 14.2737777),
    Saviano("Saviano", 40.9091720, 14.5091650),
    Palma_Campania("Palma Campania", 40.8659444, 14.5497037),
    Casandrino("Casandrino", 40.9307810, 14.2478830),
    Massa_Lubrense("Massa Lubrense", 40.6108260, 14.3449680),
    Pollena_Trocchia("Pollena Trocchia", 40.8563542, 14.3824449),
    Piano_di_Sorrento("Piano di Sorrento", 40.6365892, 14.4070120),
    Cicciano("Cicciano", 40.9622675, 14.5372763),
    Monte_di_Procida("Monte di Procida", 40.8020555, 14.0528333),
    Crispano("Crispano", 40.9547326, 14.2861531),
    Parthenope("Parthenope", 40.343434, 14.34343432);


    Comuni(String nome, double latitudine, double longitudine) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    private final String nome;

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    private final double latitudine;
    private final double longitudine;


}
