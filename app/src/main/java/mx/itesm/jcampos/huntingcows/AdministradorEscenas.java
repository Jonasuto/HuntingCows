package mx.itesm.jcampos.huntingcows;

import org.andengine.engine.Engine;

/**
 * Administra la escena que se verá en la pantalla
 */
public class AdministradorEscenas {
    // Instancia única
    private static final AdministradorEscenas INSTANCE = new AdministradorEscenas();
    protected mx.itesm.jcampos.huntingcows.ControlJuego actividadJuego;

    // Declara las distintas escenas que forman el juego
    private mx.itesm.jcampos.huntingcows.EscenaBase escenaSplash;
    private mx.itesm.jcampos.huntingcows.EscenaBase escenaMenu;
    private mx.itesm.jcampos.huntingcows.EscenaBase escenaAcercaDe;
    private mx.itesm.jcampos.huntingcows.EscenaBase escenaHistoriaIntro;


    // El tipo de escena que se está mostrando
    private mx.itesm.jcampos.huntingcows.TipoEscena tipoEscenaActual = mx.itesm.jcampos.huntingcows.TipoEscena.ESCENA_SPLASH;
    // La escena que se está mostrando
    private mx.itesm.jcampos.huntingcows.EscenaBase escenaActual;
    // El engine para hacer el cambio de escenas
    private Engine engine;

    // Asigna valores iniciales del administrador, por que??
    public static void inicializarAdministrador(mx.itesm.jcampos.huntingcows.ControlJuego actividadJuego, Engine engine) {
        getInstance().actividadJuego = actividadJuego;
        getInstance().engine = engine;
    }

    // Regresa la instancia del administrador de escenas por que es importante regresar la instancia?
    public static AdministradorEscenas getInstance() {

        return INSTANCE;
    }

    // Regresa el tipo de la escena actual
    public mx.itesm.jcampos.huntingcows.TipoEscena getTipoEscenaActual() {

        return tipoEscenaActual;
    }

    // Regresa la escena actual
    public mx.itesm.jcampos.huntingcows.EscenaBase getEscenaActual() {

        return escenaActual;
    }

    /*
     * Pone en la pantalla la escena que llega como parámetro y guarda el nuevo estado
     */
    private void setEscenaBase(mx.itesm.jcampos.huntingcows.EscenaBase nueva) {
        engine.setScene(nueva);
        escenaActual = nueva;
        tipoEscenaActual = nueva.getTipoEscena();
    }

    /**
     * Cambia a la escena especificada en el parámetro
     *
     * @param nuevoTipo la nueva escena que se quiere mostrar
     */
    public void setEscena(mx.itesm.jcampos.huntingcows.TipoEscena nuevoTipo) {
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
        }
    }

    //*** Crea la escena de Splash
    public void crearEscenaSplash() {
        // Carga los recursos
        escenaSplash = new mx.itesm.jcampos.huntingcows.EscenaSplash();
    }

    //*** Libera la escena de Splash
    public void liberarEscenaSplash() {
        escenaSplash.liberarEscena();
        escenaSplash = null;
    }

    // ** MENU
    //*** Crea la escena de MENU
    public void crearEscenaMenu() {
        // Carga los recursos
        escenaMenu = new mx.itesm.jcampos.huntingcows.EscenaMenu();
    }

    //*** Libera la escena de MENU
    public void liberarEscenaMenu() {
        escenaMenu.liberarEscena();
        escenaMenu = null;
    }

    //*** Crea la escena de Acerca De
    public void crearEscenaAcercaDe() {
        // Carga los recursos
        escenaAcercaDe = new mx.itesm.jcampos.huntingcows.EscenaAcercaDe();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaAcercaDe() {
        escenaAcercaDe.liberarEscena();
        escenaAcercaDe = null;
    }
    //*** Crea la escena de Acerca De
    public void crearEscenaHistoriaIntro() {
        // Carga los recursos
        escenaHistoriaIntro = new mx.itesm.jcampos.huntingcows.EscenaHistoriaIntro();
    }

    //*** Libera la escena de AcercDe
    public void liberarEscenaHistoriaIntro() {
        escenaHistoriaIntro.liberarEscena();
        escenaHistoriaIntro = null;
    }

}