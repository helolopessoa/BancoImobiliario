package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.GameManager;

public class ViewController extends Observer{

	private static ViewController instance = new ViewController();
	private GameManager manager;
	private String state = "";
	int carta;
	int resultadoDados[] = new int[]{0,0};
	int heightJogador;
	int widthJogador;
	int[] posXTabuleiro;
	int[] posYTabuleiro;
	int multiplicadorPosX=7;
	int multiplicadorPosY=13;
	boolean comprou;
	
	private ViewController(){
		this.posXTabuleiro = new int[11];
		this.posYTabuleiro = new int[11];
			for(int i=0; i< 11; i++) {
				posXTabuleiro[i] = 610-55*i;
			}
			for(int i=0; i< 11; i++) {
				posYTabuleiro[i] = 690-55*i;
			}
	}
	
	public static ViewController getInstance() {
		return instance;
	}
	
	void initJogadores(int n) {
		this.manager= new GameManager(this,n);
	}
	
	void encerraJogo() {
		manager.encerraJogo();
	}
	
	
	String rodaDados() {
		manager.rodaDados(2);
		resultadoDados = manager.gameState.getDados(state);
		Integer myInt1 = Integer.valueOf(resultadoDados[0]);
		String s1 = myInt1.toString();
		Integer myInt2 = Integer.valueOf(resultadoDados[1]);
		String s2 = myInt2.toString();
		String image1 = "die_face_" + s1 + ".png";
		String image2 = "die_face_" + s2 + ".png";
		return image1+image2;
	}
	
	BufferedImage getImageJogador(int i) {
		String pathJogador = "Imagens-01/pinos/";
		BufferedImage image = null;
		String imageName = pathJogador + "pin" + i + ".png";
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName));
		} catch(IOException e) {
			System.out.println("getImageJogador falhou");
			e.printStackTrace();
		}
		this.heightJogador = image.getHeight();
		this.widthJogador = image.getWidth();
		return image;
	}
	
	
	int[] getPosJogador(int jogador) {
		System.out.println("getPosJogadores");
		int pos = manager.gameState.getPosJogador(state, jogador);
		int[] posJogador = {-1,-1};
		if(pos >= 0 && pos <= 10 ) { // y fixo x m�vel mX = 7 e mY=13
			this.multiplicadorPosX = 7;
			this.multiplicadorPosY = 13;
			posJogador[0] = posXTabuleiro[pos];				
			posJogador[1] = posYTabuleiro[0];
		}
		else if(pos >= 20 && pos <= 30 ) {
			this.multiplicadorPosX = 13;
			this.multiplicadorPosY = 7;
			posJogador[0] = posXTabuleiro[0];				
			posJogador[1] = posYTabuleiro[pos-10];
		}
		else if(pos >= 20 && pos <= 30 ) {
			this.multiplicadorPosX = 7;
			this.multiplicadorPosY = 13;
			posJogador[0] = posXTabuleiro[pos-20];				
			posJogador[1] = posYTabuleiro[11];
		}
		else {
			this.multiplicadorPosX = 13;
			this.multiplicadorPosY = 7;
			posJogador[0] = posXTabuleiro[11];				
			posJogador[1] = posYTabuleiro[pos-30];
		}
		
		return posJogador;
	}
	
	
//	
//	int getPosXJogador(int jogador) {
//		int pos = 0; //get dado from string do update
//		if(pos >= 0 && pos <= 10 ) {
//			this.multiplicadorPosX = 7;
//			return posXTabuleiro[0];
//		}
//		else if(pos >= 20 && pos <= 30 ) {
//			this.multiplicadorPosY = 7;
//			return posXTabuleiro[11];
//			
//		}
//		else {
//			for(int i=0; i< 11; i++) {
//				posXTabuleiro[i] = 690-55*i;
//			}
//		}
//		
//	}
//	
//	int getPosYJogador(int i) {
//		int pos = 0; //get dado from string do update
//		if(pos >= 0 && pos <= 10 ) {
//			this.multiplicadorPosY = 7;
//			return posYTabuleiro[0];
//		}
//		else if(pos >= 20 && pos <= 30 ) {
//			this.multiplicadorPosY = 7;
//			return posYTabuleiro[11];
//			
//		}
//		else {
//			for(int i=0; i< 11; i++) {
//				posYTabuleiro[i] = 690-55*i;
//			}
//		}
//	}
//	
	
	String getCartaSorte() {
		manager.getCartaSorte();
		carta = manager.gameState.getCarta(state);
		Integer myInt1 = Integer.valueOf(carta);
		String s1 = myInt1.toString();
		String image = "chance" + s1 + ".png";
		return image;
	}
	
	public void update() {
		this.state = manager.getState();
		System.out.println(this.state);
	}

}
