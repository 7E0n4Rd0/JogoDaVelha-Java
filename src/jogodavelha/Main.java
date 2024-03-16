package jogodavelha;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main{
    static class GameFrame extends JFrame{
    JPanel titlePanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] gameButtons = new JButton[9];
    Random random = new Random();
    public int buttonsEnabled;
    int player = random.nextInt(0, 2);
    
    GameFrame(){
        // this frame
        this.setSize(800, 640);
        this.setTitle("Jogo Da Velha");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        // Title Panel
        titlePanel.setBackground(Color.black);
        titlePanel.setBounds(0, 0, 800, 640);
        titlePanel.setOpaque(true);
        
        // Text Label
        
        textLabel.setFont(new Font("Arcade Classic", Font.BOLD, 50));
        textLabel.setForeground(new Color(150,150,150));
        titlePanel.add(textLabel);
        
        // Game Panel
        gamePanel.setBounds(90, 110, 600, 460);
        gamePanel.setOpaque(true);
        gamePanel.setVisible(true);
        gamePanel.setLayout(new GridLayout(3, 3, 21, 20));
        
        GameButtonsHandler handler = new GameButtonsHandler();
        //Making gameButtons
        for(int i = 0; i <= 8; i++){
            gameButtons[i] = new JButton();
            gameButtons[i].setVisible(false);
            gameButtons[i].setOpaque(true);
            gameButtons[i].setFocusable(false);
            gameButtons[i].setBorderPainted(false);
            gameButtons[i].setEnabled(false);
            gameButtons[i].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 10));
            gameButtons[i].setBackground(Color.black);
            gameButtons[i].setFont(new Font("Arcade Classic",Font.BOLD,70));
            gameButtons[i].setForeground(new Color(100,100,100));
            gameButtons[i].addMouseListener(handler);
            gamePanel.add(gameButtons[i]);
        }

        this.buttonsEnabled = 9;
        
        this.add(gamePanel);
        this.add(titlePanel);
        
        try {
            textLabel.setText("JOGO DA VELHA");
            gamePanel.setBackground(Color.BLACK);
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Player
        if(player == 0){ 
            textLabel.setText("Jogador X");
        }else{
            textLabel.setText("Jogador O");
        }   
        gamePanel.setBackground(new Color(240,240,240));
        for(int i = 0; i <= 8; i++){
            gameButtons[i].setVisible(true);
            gameButtons[i].setEnabled(true);
        }
    }
    
    
    
    public class GameButtonsHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            for(int i = 0; i <= 8; i++){
                if(e.getSource() == gameButtons[i] && gameButtons[i].getText().equals("")){
                    if(player == 0){
                        gameButtons[i].setText("X");
                        gameButtons[i].setEnabled(false);
                        player += 1;
                        textLabel.setText("Jogador O");
                        buttonsEnabled--;
                        check();
                    }else if(player == 1){
                        gameButtons[i].setText("O");
                        gameButtons[i].setEnabled(false);
                        player -= 1;
                        textLabel.setText("Jogador X");
                        buttonsEnabled--;
                        check();
                    }
                    isDraw();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for(int i = 0; i <= 8; i++){    
                if(e.getSource() == gameButtons[i] && gameButtons[i].isEnabled()){
                    gameButtons[i].setBorderPainted(true);
                }else if(e.getSource() == gameButtons[i] && !gameButtons[i].isEnabled()){
                    gameButtons[i].setBorderPainted(false);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for(int i = 0; i <= 8; i++){    
                if(e.getSource() == gameButtons[i]){
                    gameButtons[i].setBorderPainted(false);
                }
            }
        }
        // Methods
        public void check(){
            // Checking the X turns
            if((gameButtons[0].getText().equals("X")  &&
                gameButtons[1].getText().equals("X")  &&
                gameButtons[2].getText().equals("X") )){
                xIsWinner(0,1,2);

            }else if(gameButtons[3].getText().equals("X")  &&
                gameButtons[4].getText().equals("X")  &&
                gameButtons[5].getText().equals("X") ){
                xIsWinner(3,4,5);

            }else if(gameButtons[6].getText().equals("X")  &&
                gameButtons[7].getText().equals("X")  &&
                gameButtons[8].getText().equals("X") ){
                xIsWinner(6,7,8);

            }else if(gameButtons[0].getText().equals("X")  &&
                gameButtons[4].getText().equals("X")  &&
                gameButtons[8].getText().equals("X") ){
                xIsWinner(0,4,8);

            }else if(gameButtons[2].getText().equals("X")  &&
                gameButtons[4].getText().equals("X")  &&
                gameButtons[6].getText().equals("X") ){
                xIsWinner(2,4,6);

            }else if(gameButtons[0].getText().equals("X")  &&
                gameButtons[3].getText().equals("X")  &&
                gameButtons[6].getText().equals("X") ){
                xIsWinner(0,3,6);

            }else if(gameButtons[1].getText().equals("X")  &&
                gameButtons[4].getText().equals("X")  &&
                gameButtons[7].getText().equals("X") ){
                xIsWinner(1,4,7);

            }else if(gameButtons[2].getText().equals("X")  &&
                gameButtons[5].getText().equals("X")  &&
                gameButtons[8].getText().equals("X") ){
                xIsWinner(2,5,8);
            }

            // Checking the O turns
            if((gameButtons[0].getText().equals("O")  &&
                gameButtons[1].getText().equals("O")  &&
                gameButtons[2].getText().equals("O") )){
                oIsWinner(0,1,2);

            }else if(gameButtons[3].getText().equals("O")  &&
                gameButtons[4].getText().equals("O")  &&
                gameButtons[5].getText().equals("O") ){
                oIsWinner(3,4,5);

            }else if(gameButtons[6].getText().equals("O")  &&
                gameButtons[7].getText().equals("O")  &&
                gameButtons[8].getText().equals("O") ){
                oIsWinner(6,7,8);

            }else if(gameButtons[0].getText().equals("O")  &&
                gameButtons[4].getText().equals("O")  &&
                gameButtons[8].getText().equals("O") ){
                oIsWinner(0,4,8);
            }else if(gameButtons[2].getText().equals("O")  &&
                gameButtons[4].getText().equals("O")  &&
                gameButtons[6].getText().equals("O") ){
                oIsWinner(2,4,6);

            }else if(gameButtons[0].getText().equals("O")  &&
                gameButtons[3].getText().equals("O")  &&
                gameButtons[6].getText().equals("O") ){
                oIsWinner(0,3,6);

            }else if(gameButtons[1].getText().equals("O")  &&
                gameButtons[4].getText().equals("O")  &&
                gameButtons[7].getText().equals("O") ){
                oIsWinner(1,4,7);

            }else if(gameButtons[2].getText().equals("O")  &&
                gameButtons[5].getText().equals("O")  &&
                gameButtons[8].getText().equals("O") ){
                oIsWinner(2,5,8);
            }     
        }
    
        public void xIsWinner(int i, int j, int k){
            textLabel.setText("Jogador X VENCEU!!");
            gameButtons[i].setBackground(Color.white);
            gameButtons[j].setBackground(Color.white);
            gameButtons[k].setBackground(Color.white);
            for(int a = 0; a <= 8; a++){
                gameButtons[a].removeMouseListener(this);
                gameButtons[a].setEnabled(false);
                gameButtons[a].setBorderPainted(false);
            }
        }

        public void oIsWinner(int i, int j, int k){
            textLabel.setText("Jogador O VENCEU!!");
            gameButtons[i].setBackground(Color.white);
            gameButtons[j].setBackground(Color.white);
            gameButtons[k].setBackground(Color.white);
            for(int a = 0; a <= 8; a++){
                gameButtons[a].removeMouseListener(this);
                gameButtons[a].setEnabled(false);
                gameButtons[a].setBorderPainted(false);
            }
        }

        public void isDraw(){
            if(buttonsEnabled == 0){
                textLabel.setText("EMPATE");
                for(int i = 0; i <= 8; i++){
                    gameButtons[i].removeMouseListener(this);
                    gameButtons[i].setEnabled(false);
                    gameButtons[i].setBorderPainted(false);
                }
            }
        }
    }
}
    public static void main(String[] args) {
        new GameFrame();
        
        // Estou feliz, pois finalmente está sendo do jeito que eu quero. - 11/03/2024
        // Fiz o projeto com o nome 'Jogo da Velha' e coloquei os textos em inglês lol - 13/03/2024
        // Agora sim, eu terminei este negócio. - 14/03/2024

    }

}