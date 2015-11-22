package mx.itesm.rmroman.proyectobasegpo01;

import org.andengine.engine.Engine;

/**
 * Administra la escena que se verá en la pantalla
 */
public class AdministradorEscenas {
    // Instancia única
    private static final AdministradorEscenas INSTANCE = new AdministradorEscenas();


    protected mx.itesm.rmroman.proyectobasegpo01.ControlJuego actividadJuego;

    // Declara las distintas escenas que forman el juego
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaSplash;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaMenu;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaAcercaDe;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaHistoriaIntro;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaSplashHUntingCows;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaCazaJurasica;
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaComic;
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

    private boolean cazaJurasicaDesbloqueado=false;

    // El tipo de escena que se está mostrando
    private mx.itesm.rmroman.proyectobasegpo01.TipoEscena tipoEscenaActual = mx.itesm.rmroman.proyectobasegpo01.TipoEscena.ESCENA_SPLASH;
    // La escena que se está mostrando
    private mx.itesm.rmroman.proyectobasegpo01.EscenaBase escenaActual;
    // El engine para hacer el cambio de escenas
    public Engine engine;

    // Asigna valores iniciales del administrador, por que??
    public static void inicializarAdministrador(mx.itesm.rmroman.proyectobasegpo01.ControlJuego actividadJuego, Engine engine) {
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
    public mx.itesm.rmroman.proyectobasegpo01.TipoEscena getTipoEscenaActual() {

        return tipoEscenaActual;
    }

    // Regresa la escena actual
    public mx.itesm.rmroman.proyectobasegpo01.EscenaBase getEscenaActual() {

        return escenaActual;
    }

    /*
     * Pone en la pantalla la escena que llega como parámetro y guarda el nuevo estado
     */
    private void setEscenaBase(mx.itesm.rmroman.proyectobasegpo01.EscenaBase nueva) {
        engine.setScene(nueva);
        escenaActual = nueva;
        tipoEscenaActual = nueva.getTipoEscena();
    }

    /**
     * Cambia a la escena especificada en el parámetro
     *
     * @param nuevoTipo la nueva escena que se quiere mostrar
     */
    public void setEscena(mx.itesm.rmroman.proyectobasegpo01.TipoEscena nuevoTipo) {
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
            case ESCENA_CAZA_JURASICA_NIVEL_2:
                setEscenaBase(escenaCazaJurasicaNivel2);
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
            case ESCENA_LABERINTO_UNO:
                setEscenaBase(escenaLaberintoUno);
                break;
            case ESCENA_VIAJE_EGIPTO:
                setEscenaBase(escenaViajeEgipto);
                break;
            case ESCENA_MUNDOS_CAZA_JURASICA:
                setEscenaBase(escenaMundosCazaJurasica);
                break;
            case ESCENA_CAZA_JURASICA_RUNNER:
                setEscenaBase(escenaCazaJurasicaRunner);
                break;
        }
    }

    //*** Crea la escena de Splash
    public void crearEscenaSplash() {
        // Carga los recursos
        escenaSplash = new mx.itesm.rmroman.proyectobasegpo01.EscenaSplash();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaSplash() {
        escenaSplash.liberarEscena();
        escenaSplash = null;
    }

    public void crearEscenaCazaJurasicaRunner() {
        // Carga los recursos
        escenaCazaJurasicaRunner= new mx.itesm.rmroman.proyectobasegpo01.EscenaCazaJurasicaRunner();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaCazaJurasicaRunner() {
        escenaCazaJurasicaRunner.liberarEscena();
        escenaCazaJurasicaRunner = null;
    }

    public void crearEscenaMundosCazaJurasica() {
        // Carga los recursos
        escenaMundosCazaJurasica = new mx.itesm.rmroman.proyectobasegpo01.EscenaMundosCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaMundosCazaJurasica() {
        escenaMundosCazaJurasica.liberarEscena();
        escenaMundosCazaJurasica = null;
    }

    public void crearEscenaViajeEgipto() {
        // Carga los recursos
        escenaViajeEgipto = new mx.itesm.rmroman.proyectobasegpo01.EscenaViajeEgipto();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaViajeEgipto() {
        escenaViajeEgipto.liberarEscena();
        escenaViajeEgipto = null;
    }

    public void crearEscenaLaberintoUno() {
        // Carga los recursos
        escenaLaberintoUno = new mx.itesm.rmroman.proyectobasegpo01.EscenaLaberintoUno();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaLaberintoUno() {
        escenaLaberintoUno.liberarEscena();
        escenaLaberintoUno = null;
    }

    public void crearEscenaAleatoriedad() {
        // Carga los recursos
        escenaAleatoriedad = new mx.itesm.rmroman.proyectobasegpo01.EscenaAleatoriedad();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaAleatoriedad() {
        escenaAleatoriedad.liberarEscena();
        escenaAleatoriedad = null;
    }

    public void crearEscenaArcade() {
        // Carga los recursos
        escenaArcade = new mx.itesm.rmroman.proyectobasegpo01.EscenaArcade();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaArcade() {
        escenaArcade.liberarEscena();
        escenaArcade = null;
    }

    public void crearEscenaPeleaBossCazaJurasica() {
        // Carga los recursos
        escenaPeleaBossCazaJurasica = new mx.itesm.rmroman.proyectobasegpo01.EscenaPeleaBossCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaPeleaBossCazaJurasica() {
        escenaPeleaBossCazaJurasica.liberarEscena();
        escenaPeleaBossCazaJurasica = null;
    }

    public void crearEscenaGanaste() {
        // Carga los recursos
        escenaGanaste = new mx.itesm.rmroman.proyectobasegpo01.EscenaGanaste();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaGanaste() {
        escenaGanaste.liberarEscena();
        escenaGanaste = null;
    }

    public void crearEscenaPerdiste() {
        // Carga los recursos
        escenaPerdiste = new mx.itesm.rmroman.proyectobasegpo01.EscenaGanaste();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaPerdiste() {
        escenaPerdiste.liberarEscena();
        escenaPerdiste = null;
    }

    public void crearEscenaHistoriaCazaJurasica() {
        // Carga los recursos
        escenaHistoriaCazaJurasica = new mx.itesm.rmroman.proyectobasegpo01.EscenaHistoriaCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaHistoriaCazaJurasica() {
        escenaHistoriaCazaJurasica.liberarEscena();
        escenaHistoriaCazaJurasica = null;
    }

    public void crearEscenaCazaJurasicaNivel2() {
        // Carga los recursos
        escenaCazaJurasicaNivel2 = new mx.itesm.rmroman.proyectobasegpo01.EscenaCazaJurasicaNivel2();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaCazaJurasicaNivel2() {
        escenaCazaJurasicaNivel2.liberarEscena();
        escenaCazaJurasicaNivel2 = null;
    }

    public void crearEscenaIntroCazaJurasica() {
        // Carga los recursos
        escenaIntroCazaJurasica = new mx.itesm.rmroman.proyectobasegpo01.EscenaIntroCazaJurasica();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaIntroCazaJurasica() {
        escenaIntroCazaJurasica.liberarEscena();
        escenaIntroCazaJurasica = null;
    }

    public void crearEscenaSplashHuntingCows() {
        // Carga los recursos
        escenaSplashHUntingCows = new mx.itesm.rmroman.proyectobasegpo01.EscenaSplashHuntingCows();
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
        escenaMenu = new mx.itesm.rmroman.proyectobasegpo01.EscenaMenu();
    }

    //*** Libera la escena de MENU
    public void liberarEscenaMenu() {
        escenaMenu.liberarEscena();
        escenaMenu = null;
    }

    //*** Crea la escena de Acerca De
    public void crearEscenaAcercaDe() {
        // Carga los recursos
        escenaAcercaDe = new mx.itesm.rmroman.proyectobasegpo01.EscenaAcercaDe();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaAcercaDe() {
        escenaAcercaDe.liberarEscena();
        escenaAcercaDe = null;
    }
    //*** Crea la escena de Acerca De
    public void crearEscenaHistoriaIntro() {
        // Carga los recursos
        escenaHistoriaIntro = new mx.itesm.rmroman.proyectobasegpo01.EscenaHistoriaIntro();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaHistoriaIntro() {
        escenaHistoriaIntro.liberarEscena();
        escenaHistoriaIntro = null;
    }

    public void crearEscenaCazaJurasica() {
        // Carga los recursos


        escenaCazaJurasica = new mx.itesm.rmroman.proyectobasegpo01.EscenaCazaJurasica();
    }

    //*** Libera la escena de AcercDe

    public void liberarEscenaCazaJurasica() {
        escenaCazaJurasica.liberarEscena();
        escenaCazaJurasica = null;
    }

    public void crearEscenaComic() {
        // Carga los recursos
        escenaComic = new mx.itesm.rmroman.proyectobasegpo01.EscenaComic();
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

    public void crearEscenaAjustes() {
        // Carga los recursos
        escenaAjustes = new EscenaAjustes();
    }

    //*** Libera la escena de cargando
    public void liberarEscenaAjustes() {
        escenaAjustes.liberarEscena();
        escenaAjustes = null;
    }


}