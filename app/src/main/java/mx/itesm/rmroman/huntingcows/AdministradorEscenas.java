package mx.itesm.rmroman.huntingcows;

import org.andengine.engine.Engine;

/**
 * Administra la escena que se verá en la pantalla
 */
public class AdministradorEscenas {
    // Instancia única
    private static final AdministradorEscenas INSTANCE = new AdministradorEscenas();


    protected ControlJuego actividadJuego;

    // Declara las distintas escenas que forman el juego
    private EscenaBase escenaSplash;
    private EscenaBase escenaMenu;
    private EscenaBase escenaAcercaDe;
    private EscenaBase escenaHistoriaIntro;
    private EscenaBase escenaSplashHUntingCows;
    private EscenaBase escenaCazaJurasica;
    private EscenaBase escenaComic;
    private EscenaBase escenaCargando;
    private EscenaBase escenaAjustes;
    private EscenaBase escenaCazaJurasicaNivel2;
    private EscenaBase escenaIntroCazaJurasica;
    private EscenaBase escenaHistoriaCazaJurasica;
    private EscenaBase escenaPerdiste;
    private EscenaBase escenaGanaste;
    private EscenaBase escenaArcade;
    private EscenaBase escenaLaberintoUno;
    private EscenaBase escenaAleatoriedad;
    private EscenaBase escenaPeleaBossCazaJurasica;
    private EscenaBase escenaViajeEgipto;
    private EscenaBase escenaMundosCazaJurasica;
    private EscenaBase escenaCazaJurasicaRunner;
    private EscenaBase escenaPirata;
    private EscenaBase escenaJurasicaLvl2;
    private EscenaBase escenaHistoriaPirata;
    private EscenaBase escenaHistoriaEgipto;

    private boolean cazaJurasicaDesbloqueado=false;

    // El tipo de escena que se está mostrando
    private TipoEscena tipoEscenaActual = TipoEscena.ESCENA_SPLASH;
    // La escena que se está mostrando
    private EscenaBase escenaActual;
    // El engine para hacer el cambio de escenas
    public Engine engine;

    // Asigna valores iniciales del administrador, por que??
    public static void inicializarAdministrador(ControlJuego actividadJuego, Engine engine) {
        getInstance().actividadJuego = actividadJuego;
        getInstance().engine = engine;
    }

    // Regresa la instancia del administrador de escenas por que es importante regresar la instancia?
    public static AdministradorEscenas getInstance() {

        return INSTANCE;
    }

    public boolean getcazaJurasicaDesbloqueado(){
        return cazaJurasicaDesbloqueado;
    }

    public void setcazaJurasicaDesbloqueado(boolean desbloqueo){
        this.cazaJurasicaDesbloqueado=desbloqueo;
    }

    // Regresa el tipo de la escena actual
    public TipoEscena getTipoEscenaActual() {

        return tipoEscenaActual;
    }

    // Regresa la escena actual
    public EscenaBase getEscenaActual() {

        return escenaActual;
    }

    /*
     * Pone en la pantalla la escena que llega como parámetro y guarda el nuevo estado
     */
    private void setEscenaBase(EscenaBase nueva) {
        engine.setScene(nueva);
        escenaActual = nueva;
        tipoEscenaActual = nueva.getTipoEscena();
    }

    /**
     * Cambia a la escena especificada en el parámetro
     *
     * @param nuevoTipo la nueva escena que se quiere mostrar
     */
    public void setEscena(TipoEscena nuevoTipo) {
        switch (nuevoTipo) {
            case ESCENA_SPLASH:
                setEscenaBase(escenaSplash);
                break;
            case ESCENA_MENU:
                setEscenaBase(escenaMenu);
                break;
            case ESCENA_ACERCA_DE:
                setEscenaBase(escenaAcercaDe);
                break;
            case ESCENA_HISTORIA_INTRO:
                setEscenaBase(escenaHistoriaIntro);
                break;
            case ESCENA_SPLASH_HUNTING_COWS:
                setEscenaBase(escenaSplashHUntingCows);
                break;
            case ESCENA_CAZA_JURASICA:
                setEscenaBase(escenaCazaJurasica);
                break;
            case ESCENA_COMIC:
                setEscenaBase(escenaComic);
                break;
            case ESCENA_CARGANDO:
                setEscenaBase(escenaCargando);
                break;
            case ESCENA_AJUSTES:
                setEscenaBase(escenaAjustes);
                break;
            case ESCENA_INTRO_CAZA_JURASICA:
                setEscenaBase(escenaIntroCazaJurasica);
                break;
            case ESCENA_HISTORIA_CAZA_JURASICA:
                setEscenaBase(escenaHistoriaCazaJurasica);
                break;
            case ESCENA_PERDISTE:
                setEscenaBase(escenaPerdiste);
                break;
            case ESCENA_GANASTE:
                setEscenaBase(escenaGanaste);
                break;
            case ESCENA_ESCENA_PELEA_BOSS_CAZA_JURASICA:
                setEscenaBase(escenaPeleaBossCazaJurasica);
                break;
            case ESCENA_ARCADE:
                setEscenaBase(escenaArcade);
                break;
            case ESCENA_ALEATORIEDAD:
                setEscenaBase(escenaAleatoriedad);
                break;
            case ESCENA_VIAJE_EGIPTO:
                setEscenaBase(escenaViajeEgipto);
                break;
            case ESCENA_MUNDOS_CAZA_JURASICA:
                setEscenaBase(escenaMundosCazaJurasica);
                break;
            case ESCENA_PIRATA:
                setEscenaBase(escenaPirata);
                break;
            case ESCENA_CAZA_JURASICA_LVL2:
                setEscenaBase(escenaJurasicaLvl2);
                break;
            case ESCENA_HISTORIA_PIRATA:
                setEscenaBase(escenaHistoriaPirata);
                break;
            case ESCENA_HISTORIA_EGIPTO:
                setEscenaBase(escenaHistoriaEgipto);
                break;
        }
    }

    //*** Crea la escena de Splash
    public void crearEscenaSplash() {
        // Carga los recursos
        escenaSplash = new EscenaSplash();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaSplash() {
        escenaSplash.liberarEscena();
        escenaSplash = null;
    }

    public void crearEscenaHistoriaEgipto() {
        // Carga los recursos
        escenaHistoriaEgipto = new EscenaHistoriaEgipto();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaHistoriaEgipto() {
        escenaHistoriaEgipto.liberarEscena();
        escenaHistoriaEgipto = null;
    }

    public void crearEscenaHistoriaPirata() {
        // Carga los recursos
        escenaHistoriaPirata = new EscenaHistoriaPirata();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaHistoriaPirata() {
        escenaHistoriaPirata.liberarEscena();
        escenaHistoriaPirata = null;
    }

    public void crearEscenaCazaJurasicaLvl2() {
        // Carga los recursos
        escenaJurasicaLvl2 = new EscenaCazaJurasicaLvl2();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaCazaJurasicaLvl2() {
        escenaJurasicaLvl2.liberarEscena();
        escenaJurasicaLvl2 = null;
    }

    public void crearEscenaPirata() {
        // Carga los recursos
        escenaPirata = new EscenaOdiseaPirata();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaPirata() {
        escenaPirata.liberarEscena();
        escenaPirata = null;
    }


    public void crearEscenaMundosCazaJurasica() {
        // Carga los recursos
        escenaMundosCazaJurasica = new EscenaMundosCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaMundosCazaJurasica() {
        escenaMundosCazaJurasica.liberarEscena();
        escenaMundosCazaJurasica = null;
    }

    public void crearEscenaViajeEgipto() {
        // Carga los recursos
        escenaViajeEgipto = new EscenaViajeEgipto();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaViajeEgipto() {
        escenaViajeEgipto.liberarEscena();
        escenaViajeEgipto = null;
    }


    public void crearEscenaAleatoriedad() {
        // Carga los recursos
        escenaAleatoriedad = new EscenaAleatoriedad();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaAleatoriedad() {
        escenaAleatoriedad.liberarEscena();
        escenaAleatoriedad = null;
    }

    public void crearEscenaArcade() {
        // Carga los recursos
        escenaArcade = new EscenaArcade();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaArcade() {
        escenaArcade.liberarEscena();
        escenaArcade = null;
    }

    public void crearEscenaGanaste(int da) {
        // Carga los recursos
        escenaGanaste = new EscenaGanaste(da);
    }

    //*** Libera la escena de Splash
    public void liberarEscenaGanaste() {
        escenaGanaste.liberarEscena();
        escenaGanaste = null;
    }

    public void crearEscenaPerdiste(int num) {
        // Carga los recursos
        escenaPerdiste = new EscenaPerdiste(num);
    }

    //*** Libera la escena de Splash
    public void liberarEscenaPerdiste() {
        escenaPerdiste.liberarEscena();
        escenaPerdiste = null;
    }

    public void crearEscenaHistoriaCazaJurasica() {
        // Carga los recursos
        escenaHistoriaCazaJurasica = new EscenaHistoriaCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaHistoriaCazaJurasica() {
        escenaHistoriaCazaJurasica.liberarEscena();
        escenaHistoriaCazaJurasica = null;
    }

    public void crearEscenaIntroCazaJurasica() {
        // Carga los recursos
        escenaIntroCazaJurasica = new EscenaIntroCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaIntroCazaJurasica() {
        escenaIntroCazaJurasica.liberarEscena();
        escenaIntroCazaJurasica = null;
    }

    public void crearEscenaSplashHuntingCows() {
        // Carga los recursos
        escenaSplashHUntingCows = new EscenaSplashHuntingCows();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaSplashHuntingCows() {
        escenaSplashHUntingCows.liberarEscena();
        escenaSplashHUntingCows = null;
    }

    // ** MENU
    //*** Crea la escena de MENU
    public void crearEscenaMenu() {
        // Carga los recursos
        escenaMenu = new EscenaMenu();
    }

    //*** Libera la escena de MENU
    public void liberarEscenaMenu() {
        escenaMenu.liberarEscena();
        escenaMenu = null;
    }

    //*** Crea la escena de Acerca De
    public void crearEscenaAcercaDe() {
        // Carga los recursos
        escenaAcercaDe = new EscenaAcercaDe();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaAcercaDe() {
        escenaAcercaDe.liberarEscena();
        escenaAcercaDe = null;
    }
    //*** Crea la escena de Acerca De
    public void crearEscenaHistoriaIntro() {
        // Carga los recursos
        escenaHistoriaIntro = new EscenaHistoriaIntro();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaHistoriaIntro() {
        escenaHistoriaIntro.liberarEscena();
        escenaHistoriaIntro = null;
    }

    public void crearEscenaCazaJurasica() {
        // Carga los recursos


        escenaCazaJurasica = new EscenaCazaJurasica();
    }

    //*** Libera la escena de AcercDe

    public void liberarEscenaCazaJurasica() {
        escenaCazaJurasica.liberarEscena();
        escenaCazaJurasica = null;
    }

    public void crearEscenaComic() {
        // Carga los recursos
        escenaComic = new EscenaComic();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaComic() {
        escenaComic.liberarEscena();
        escenaComic = null;
    }


    //*** Crea la escena de cargando
    public void crearEscenaCargando(int numero) {
        // Carga los recursos
        escenaCargando = new EscenaCargando(numero);
    }

    //*** Libera la escena de cargando
    public void liberarEscenaCargando() {
        escenaCargando.liberarEscena();
        escenaCargando = null;
    }
}