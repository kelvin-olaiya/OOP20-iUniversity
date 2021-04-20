package iuniversity.view;

import iuniversity.controller.Controller;

public interface View {

    /**
     * 
     * @return the current controller
     */
    Controller getController();

    /**
     * 
     * @param controller the controller to be set
     */
    void setController(Controller controller);

    /**
     * This method is called when the view starts communicating with its controller.
     */
    void start();

    /**
     * 
     * @param message the error message to be displayed.
     */
    void showErrorMessage(String message);

}
