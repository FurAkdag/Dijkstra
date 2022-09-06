package my_project.model;

import KAGO_framework.model.abitur.netz.Client;
import my_project.control.ProgramController;

public class Server extends Client {

    ProgramController programController;
    public Server(ProgramController programController) {
        super("10.17.128.71", 1337);
        this.programController = programController;
    }

    @Override
    public void processMessage(String pMessage) {
        System.out.println(pMessage);
    }

    @Override
    public void send(String pMessage) {
        super.send(pMessage);
    }
}
