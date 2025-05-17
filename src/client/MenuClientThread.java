package client;

import java.awt.*;
import java.io.*;

public class MenuClientThread extends Thread{
    private BufferedReader in;
    GameClient client;
    
    public MenuClientThread(GameClient client)
    {
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
        }catch(Exception e) {}
    }
    
    public void run() {
        String mess;
        try{
            while((mess = in.readLine()) != null) {
                System.out.println("SERVER RESPONSE: " + mess);
                if(mess.startsWith("GAME")){
                    handleCommands(mess);
                } else if(!mess.equals("")){
                    client.getUi().getMessageTextArea().append(mess + "\n");
                    client.getUi().getMessageTextArea().scrollRectToVisible(new Rectangle(0, client.getUi().getMessageTextArea().getHeight()-2, 1, 1));
                }
                else {
                    System.out.println("EMPTY MESSAGE: " + mess);
                }
            }
            in.close();
        } catch(IOException e)
        {
            client.getUi().getMessageTextArea().append("Error " + e.getMessage());
            client.getUi().getReconnectButton().setVisible(true);
        }
    }
    
    private void handleCommands(String mess)
    {
        String[] parts = mess.split(">");
        CardPanel ui = client.getUi();
        String gameId;
        String sender;
        String info;
        String target;
        String command = parts[0];
        switch(command)
        {
            case "GAME_WAITING":
                ui.getMessageTextArea().append("Waiting for opponent...\n");
                client.findGame();
                break;
            case "GAME_WAITING_CANCEL":
                client.cancelFindGame();
                break;
            case "GAME_FOUND":
                gameId = parts[1];
                String enemyName = parts[2];
                String enemyChampion = parts[3];
//                ui.getMessageTextArea().append("GAMEID: " + gameId);
//                ui.getMessageTextArea().append("YOUR OPPONENT IS " + enemyName + "\n");
                client.foundOponent(gameId, enemyName, enemyChampion);
                break;
            case "GAME_FORFEIT":
                client.gameResult(false);
                break;
            case "GAME_ENEMY_FORFEIT":
                client.gameResult(true);
                break;
            case "GAME_NOT_YOUR_TURN":
                client.notMyTurn();
                break;
            case "GAME_APPLY_STATE":
                gameId = parts[1];
                sender = parts[2];
                double senderHp = Double.parseDouble(parts[3]);
                target = parts[4];
                double targetHp = Double.parseDouble(parts[5]);
                client.adjustClientState(gameId, sender, senderHp, target, targetHp);
                break;
            case "GAME_APPLY_LOGS":
                sender = parts[1];
                try{
                    info = parts[2];
                } catch(ArrayIndexOutOfBoundsException ex) {
                    info = "";
                }
                client.adjustClientState(sender, info);
                break;
            case "GAME_APPLY_ABILITY_COUNT":
                int abilityIndex = Integer.parseInt(parts[1]);
                client.decreaseAbility(abilityIndex);
                break;
            case "GAME_RESULT_WIN":
                client.gameResult(true);
                break;
            case "GAME_RESULT_LOSE":
                client.gameResult(false);
                break;
            case "GAME_GET_STATS":
                target = parts[1];
                String total = parts[2];
                String win = parts[3];
                String lose = parts[4];
                String players = parts[5];
                client.applyStats(target, total, win, lose, players);
                break;
        }
    }
}