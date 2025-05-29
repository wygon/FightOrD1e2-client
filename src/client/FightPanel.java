package client;

import championAssets.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import server.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author wygon
 */
public class FightPanel extends javax.swing.JFrame {

    private CardPanel ui;
    private String gameId;
    private Champion allyChampion;
    private GameClient client;

    private String enemyName;
    private String enemyChampionName;
    private Champion enemyChampion;
    private JButton[] buttonList;
    private Map<JButton, Pair> buttonValueMap;
    private String lastInfo;

    public FightPanel(String gameId, GameClient client, String enemyName, String enemyChampionName) {
        this.gameId = gameId;
        this.client = client;
        this.ui = client.getUi();
        this.enemyName = enemyName;
        this.enemyChampionName = enemyChampionName;
        buttonList = new JButton[6];
        buttonValueMap = new HashMap<>();
        allyChampion = client.choosenChampion;

        Optional<Champion> ec = ui.getChampionListModel().stream()
                .filter(p -> p.getName().equals(enemyChampionName))
                .findAny();

        enemyChampion = ec.get();

        initComponents();
        AfterInit();
    }

    void AfterInit() {
        addWindowListener(new OnWindowClose(this));
        setResizable(false);
        Ability[] abilities = allyChampion.getAbilities();
        allyChampionNameLabel.setText(client.name + " " + allyChampion.getName());
        Image aimg = allyChampion.getIcon().getImage();
        Image ascaledImg = aimg.getScaledInstance(allyChampionPanel.getWidth(), -1, Image.SCALE_SMOOTH);
        allyChampionIconLabel.setIcon(new ImageIcon(ascaledImg));

        allyChampionHpBar.setMaximum((int) allyChampion.getHP());
        allyChampionHpBar.setValue((int) allyChampion.getHP());
        allyChampionHpLabel.setText(String.valueOf(allyChampion.getHP()));// + " / " + String.valueOf(allyChampion.getMaxHP()));
        allyChampionHpLabel2.setText(" / " + String.valueOf(allyChampion.getMaxHP()));
        
        //==========================================
        enemyChampionNameLabel.setText(enemyName + " " + enemyChampion.getName());
//        Image eimg = enemyChampion.getIcon().getImage();
//        Image escaledImg = eimg.getScaledInstance(enemyChampionPanel.getWidth(), -1, Image.SCALE_SMOOTH);
//        enemyChampionIconLabel.setIcon(new ImageIcon(escaledImg));

        ImageIcon eimg = enemyChampion.getIcon();
        int originalW = eimg.getIconWidth();
        int originalH = eimg.getIconHeight();
        
        BufferedImage originalImg = new BufferedImage(originalW, originalH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = originalImg.createGraphics();
        g2.drawImage(eimg.getImage(), 0, 0, null);
        g2.dispose();
        
        Image escaledImg = originalImg.getScaledInstance(enemyChampionPanel.getWidth(), -1, Image.SCALE_SMOOTH);
        
        BufferedImage scaledBuffered = new BufferedImage(escaledImg.getWidth(null),escaledImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = scaledBuffered.createGraphics();
        g3.drawImage(escaledImg, 0, 0, null);
        g3.dispose();
        BufferedImage flipped = ui.flipImage(scaledBuffered);
        enemyChampionIconLabel.setIcon(new ImageIcon(flipped));
        
        enemyChampionHpBar.setMaximum((int) enemyChampion.getHP());
        enemyChampionHpBar.setValue((int) enemyChampion.getHP());
        enemyChampionHpLabel.setText(String.valueOf(enemyChampion.getHP()));// + " / " + String.valueOf(enemyChampion.getMaxHP()));
        enemyChampionHpLabel2.setText(" / " + String.valueOf(enemyChampion.getMaxHP()));
        
        ui.getMessageTextArea().append("GAME STARTED ID: " + gameId + "\n");
        logTextArea.setText("GAME ID: " + gameId + "\n" + allyChampion.getName() + " VS " + enemyChampion.getName() + "\n");

        buttonList[0] = ability1;
        buttonList[1] = ability2;
        buttonList[2] = ability3;
        buttonList[3] = ability4;
        buttonList[4] = ability5;
        buttonList[5] = ability6;

        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setVisible(false);
            buttonList[i].setText("");
            if (abilities.length > i) {
                JLabel nameLabel = new JLabel(abilities[i].getName());
                JLabel usesLeftLabel = new JLabel(abilities[i].getUsesLeft() + "");

                Pair p = new Pair(nameLabel, usesLeftLabel);
                buttonValueMap.put(buttonList[i], p);

                buttonList[i].setLayout(new BorderLayout());
                JPanel cp = new JPanel(new BorderLayout());
                cp.setOpaque(false);

                cp.add(nameLabel, BorderLayout.WEST);
                cp.add(usesLeftLabel, BorderLayout.EAST);
                buttonList[i].add(cp);
                buttonList[i].setVisible(true);
            }
        }
        lastInfo = "";
    }

//    public static void applyClosing(JFrame frame) extends WindowClosing{
////            frame.addWindowListener
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allyChampionPanel = new javax.swing.JPanel();
        allyChampionIconLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        allyChampionHpBar = new javax.swing.JProgressBar();
        allyChampionHpLabel2 = new javax.swing.JLabel();
        allyChampionHpLabel = new javax.swing.JLabel();
        allyChampionNameLabel = new javax.swing.JLabel();
        enemyChampionPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        enemyChampionHpBar = new javax.swing.JProgressBar();
        enemyChampionHpLabel2 = new javax.swing.JLabel();
        enemyChampionHpLabel = new javax.swing.JLabel();
        enemyChampionNameLabel = new javax.swing.JLabel();
        enemyChampionIconLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        autoAttackButton1 = new javax.swing.JButton();
        endTourButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();
        ability1 = new javax.swing.JButton();
        ability2 = new javax.swing.JButton();
        ability3 = new javax.swing.JButton();
        ability4 = new javax.swing.JButton();
        ability5 = new javax.swing.JButton();
        ability6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        allyChampionPanel.setPreferredSize(new java.awt.Dimension(300, 400));
        allyChampionPanel.setLayout(new java.awt.BorderLayout());

        allyChampionIconLabel.setText("PHOTO");
        allyChampionIconLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        allyChampionPanel.add(allyChampionIconLabel, java.awt.BorderLayout.CENTER);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        allyChampionHpBar.setForeground(new java.awt.Color(76, 255, 0));
        allyChampionHpBar.setValue(66);

        allyChampionHpLabel2.setText("HEALTH/MAXHEALTH");

        allyChampionHpLabel.setText("HEALTH/MAXHEALTH");

        allyChampionNameLabel.setText("ALLY NAME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allyChampionHpBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(allyChampionHpLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(allyChampionHpLabel2))
                            .addComponent(allyChampionNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(allyChampionHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allyChampionHpLabel)
                    .addComponent(allyChampionHpLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(allyChampionNameLabel))
        );

        allyChampionPanel.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        enemyChampionPanel.setPreferredSize(new java.awt.Dimension(300, 400));
        enemyChampionPanel.setLayout(new java.awt.BorderLayout());

        enemyChampionHpBar.setForeground(new java.awt.Color(255, 0, 0));
        enemyChampionHpBar.setValue(100);

        enemyChampionHpLabel2.setText("HEALTH/MAXHEALTH");

        enemyChampionHpLabel.setText("HEALTH/MAXHEALTH");

        enemyChampionNameLabel.setText("ENEMY NAME");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(enemyChampionHpLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enemyChampionHpLabel2)
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(enemyChampionNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(enemyChampionHpBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enemyChampionHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enemyChampionHpLabel)
                    .addComponent(enemyChampionHpLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enemyChampionNameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        enemyChampionPanel.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        enemyChampionIconLabel.setText("PHOTO");
        enemyChampionIconLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        enemyChampionPanel.add(enemyChampionIconLabel, java.awt.BorderLayout.CENTER);

        autoAttackButton1.setText("Auto Attack");
        autoAttackButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoAttackButton1ActionPerformed(evt);
            }
        });

        endTourButton.setText("End tour");
        endTourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTourButtonActionPerformed(evt);
            }
        });

        leaveButton.setText("Forfeit");
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        ability1.setText("skill1");
        ability1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability1ActionPerformed(evt);
            }
        });

        ability2.setText("skill2");
        ability2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability2ActionPerformed(evt);
            }
        });

        ability3.setText("skill3");
        ability3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability3ActionPerformed(evt);
            }
        });

        ability4.setText("skill4");
        ability4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability4ActionPerformed(evt);
            }
        });

        ability5.setText("skill5");
        ability5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability5ActionPerformed(evt);
            }
        });

        ability6.setText("skill6");
        ability6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ability6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(autoAttackButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(ability1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ability2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(endTourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(ability3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(ability4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(leaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ability5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(ability6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ability1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoAttackButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ability2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ability4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ability3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ability6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ability5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(leaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        logTextArea.setColumns(20);
        logTextArea.setLineWrap(true);
        logTextArea.setRows(5);
        logTextArea.setText("[wygon][hit]: ashwaganda\n[wygon][hit]: ashwaganda\n[timoti][hit]: bombaklat\n[wygon][APPROVED]\n[wygon][FLED]\n[timoti] WON THE GAME!");
        logTextArea.setFocusable(false);
        jScrollPane1.setViewportView(logTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allyChampionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemyChampionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemyChampionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(allyChampionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endTourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTourButtonActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "98");
    }//GEN-LAST:event_endTourButtonActionPerformed

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveButtonActionPerformed
        // TODO add your handling code here:\
        int returnToLobby = JOptionPane.showConfirmDialog(
                this,
                "Back to main menu",
                "Return",
                JOptionPane.YES_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (returnToLobby == JOptionPane.YES_OPTION) {
            client.sendMessage(GameCommand.FORFEIT.toString() + ">" + gameId + ">" + client.choosenChampion.getName() + ">" + enemyName);
            client.sendMessage(GameCommand.ATTACK.toString() + ">" + 
                    gameId + ">" + 
                    client.name + ">" + 
                    "135");
        }
    }//GEN-LAST:event_leaveButtonActionPerformed

    private void autoAttackButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoAttackButton1ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "0");
    }//GEN-LAST:event_autoAttackButton1ActionPerformed

    private void ability1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability1ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "1");
    }//GEN-LAST:event_ability1ActionPerformed

    private void ability2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability2ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "2");
    }//GEN-LAST:event_ability2ActionPerformed

    private void ability3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability3ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "3");
    }//GEN-LAST:event_ability3ActionPerformed

    private void ability4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability4ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "4");
    }//GEN-LAST:event_ability4ActionPerformed

    private void ability5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability5ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "5");
    }//GEN-LAST:event_ability5ActionPerformed

    private void ability6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ability6ActionPerformed
        // TODO add your handling code here:
        client.sendMessage(
                GameCommand.ATTACK.toString() + ">"
                + gameId + ">"
                + client.name + ">"
                + "6");
    }//GEN-LAST:event_ability6ActionPerformed

    
    public void addGameMessage(String mess) {
        logTextArea.append(mess + "\n");
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
    }

    public void adjustUi(String gameId, String sender, String target, Double hp) {
        if (target.equals(client.name)) {
            double hpParsed = Double.parseDouble(allyChampionHpLabel.getText()) -  hp;
            allyChampionHpLabel.setText(String.valueOf(hpParsed));
            allyChampionHpBar.setValue((int) hpParsed);
        } else {
            double hpParsed = Double.parseDouble(enemyChampionHpLabel.getText()) -  hp;
            enemyChampionHpLabel.setText(String.valueOf(hpParsed));
            enemyChampionHpBar.setValue((int) hpParsed);
        }
        logTextArea.append(sender + "] hit [" + target + "] " + hp + "\n");
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
    }

    public void adjustClientState(String gameId, String sender, double senderHp, String target, double targetHp) {
        allyChampionHpLabel.setText(
                String.valueOf((int) senderHp)
        );
        allyChampionHpBar.setValue((int) senderHp);
        enemyChampionHpLabel.setText(
                String.valueOf((int) targetHp)
        );
        enemyChampionHpBar.setValue((int) targetHp);
    }

    public void adjustClientState(String sender, String info) {
//        if (!sender.equals(client.name)) {
//            return;
//        }
        if (lastInfo.isBlank()) {
            lastInfo = info;
        }
        boolean sendToMe = true;
        if(info.contains("left!") && !sender.equals(client.name)) sendToMe = false;
        System.out.println("LAST INFO: " + lastInfo + "\n NOW INFO: " + info);
        if (logTextArea.getText().endsWith(info + "\n") && info.contains("left!") && sender.equals(client.name)) {
            logTextArea.setForeground(Color.RED);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            logTextArea.setForeground(Color.WHITE);
        } else if(sendToMe) {
            logTextArea.append(info + "\n");
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
        lastInfo = info + "\n";
    }

    public void addLogMessage(String mess) {
        logTextArea.setForeground(Color.RED);
        if (!logTextArea.getText().endsWith("TURN\n")) {
            logTextArea.append(mess + "\n");
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {}
        finally{
            if(UIManager.getLookAndFeel().getClass().getName().equals("com.formdev.flatlaf.FlatDarkLaf") || 
               UIManager.getLookAndFeel().getClass().getName().equals("com.formdev.flatlaf.FlatDarculaLaf"))
                logTextArea.setForeground(Color.WHITE);
            else
                logTextArea.setForeground(Color.BLACK);
        }
    }

    public void decreaseAbility(int abilityIndex) {
        Pair labels = buttonValueMap.get(buttonList[abilityIndex]);
        int newValue = Integer.parseInt(labels.right.getText()) - 1;
        labels.right.setText(String.valueOf(newValue));
    }

    class Pair {

        JLabel left;
        JLabel right;

        Pair(JLabel l, JLabel r) {
            left = l;
            right = r;
        }
    }

    class OnWindowClose extends WindowAdapter {

        private JFrame frame;

        public OnWindowClose(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            int returnToLobby = JOptionPane.showConfirmDialog(
                    frame,
                    "Back to main menu",
                    "Return",
                    JOptionPane.YES_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (returnToLobby == JOptionPane.YES_OPTION) {
                client.sendMessage(GameCommand.FORFEIT.toString() + ">" + gameId + ">" + client.choosenChampion.getName() + ">" + enemyName);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ability1;
    private javax.swing.JButton ability2;
    private javax.swing.JButton ability3;
    private javax.swing.JButton ability4;
    private javax.swing.JButton ability5;
    private javax.swing.JButton ability6;
    private javax.swing.JProgressBar allyChampionHpBar;
    private javax.swing.JLabel allyChampionHpLabel;
    private javax.swing.JLabel allyChampionHpLabel2;
    private javax.swing.JLabel allyChampionIconLabel;
    private javax.swing.JLabel allyChampionNameLabel;
    private javax.swing.JPanel allyChampionPanel;
    private javax.swing.JButton autoAttackButton1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton endTourButton;
    private javax.swing.JProgressBar enemyChampionHpBar;
    private javax.swing.JLabel enemyChampionHpLabel;
    private javax.swing.JLabel enemyChampionHpLabel2;
    private javax.swing.JLabel enemyChampionIconLabel;
    private javax.swing.JLabel enemyChampionNameLabel;
    private javax.swing.JPanel enemyChampionPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leaveButton;
    private javax.swing.JTextArea logTextArea;
    // End of variables declaration//GEN-END:variables
}
