package main.java.br.hidrometro.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * Classe estática para gerar uma imagem de um hidrômetro com base em leituras específicas.
 */
public class Render {

    private static final int LARGURA = 800;
    private static final int ALTURA = 500;
    private static final int CENTRO_X = LARGURA / 2;
    private static final int CENTRO_Y = ALTURA / 2;
    private static final int RAIO_MOSTRADOR = 180;

    private static final Color COR_CORPO = new Color(0, 80, 150);
    private static final Color COR_MOSTRADOR = Color.WHITE;
    private static final Color COR_TEXTO_PRETO = Color.BLACK;
    private static final Color COR_PONTEIRO_VERMELHO = new Color(220, 0, 0);
    private static final Color COR_FUNDO_ODOMETRO_VERMELHO = new Color(220, 0, 0);

    public static BufferedImage gerarImagem(double odometro, char posicaoRelogioLitros, char posicaoRelogioDecilitros) {
        BufferedImage imagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagem.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        desenharCorpo(g2d);
        desenharMostrador(g2d);
        desenharOdometro(g2d, odometro);
        
        Point centroRelogioLitros = new Point(CENTRO_X + 80, CENTRO_Y + 90);
        desenharRelogios(g2d, centroRelogioLitros, "Litros");
        desenharPonteiro(g2d, centroRelogioLitros, posicaoRelogioLitros, 30);

        Point centroRelogioDecilitros = new Point(CENTRO_X - 80, CENTRO_Y + 90);
        desenharRelogios(g2d, centroRelogioDecilitros, "Décimos de litros");
        desenharPonteiro(g2d, centroRelogioDecilitros, posicaoRelogioDecilitros, 30);
        
        g2d.dispose();
        return imagem;
    }

    private static void desenharCorpo(Graphics2D g2d) {
        g2d.setColor(COR_CORPO);
        g2d.fillRect(0, CENTRO_Y - 80, LARGURA, 160);
        g2d.fillOval(CENTRO_X - RAIO_MOSTRADOR - 50, CENTRO_Y - RAIO_MOSTRADOR - 50, (RAIO_MOSTRADOR + 50) * 2, (RAIO_MOSTRADOR + 50) * 2);
    }

    private static void desenharMostrador(Graphics2D g2d) {
        g2d.setColor(COR_MOSTRADOR);
        g2d.fillOval(CENTRO_X - RAIO_MOSTRADOR, CENTRO_Y - RAIO_MOSTRADOR, RAIO_MOSTRADOR * 2, RAIO_MOSTRADOR * 2);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(CENTRO_X - RAIO_MOSTRADOR, CENTRO_Y - RAIO_MOSTRADOR, RAIO_MOSTRADOR * 2, RAIO_MOSTRADOR * 2);
    }

    private static void desenharOdometro(Graphics2D g2d, double valorOdometro) {
        int valorInt = (int) Math.floor(valorOdometro);
        DecimalFormat df = new DecimalFormat("00000");
        String textoOdometro = df.format(valorInt);
        
        int odometroWidth = 200;
        int odometroHeight = 50;
        int odometroX = CENTRO_X - odometroWidth / 2;
        int odometroY = CENTRO_Y - 120;
        int digitoWidth = odometroWidth / 5;

        g2d.setFont(new Font("Courier New", Font.BOLD, 48));
        
        for (int i = 0; i < 5; i++) {
            int xPos = odometroX + i * digitoWidth;
            if (i == 4) {
                g2d.setColor(COR_FUNDO_ODOMETRO_VERMELHO);
                g2d.fillRect(xPos, odometroY, digitoWidth, odometroHeight);
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.drawRect(xPos, odometroY, digitoWidth, odometroHeight);
                g2d.setColor(Color.BLACK);
            }
            
            String digito = String.valueOf(textoOdometro.charAt(i));
            FontMetrics fm = g2d.getFontMetrics();
            int stringWidth = fm.stringWidth(digito);
            g2d.drawString(digito, xPos + (digitoWidth - stringWidth) / 2, odometroY + odometroHeight - fm.getDescent());
        }

        g2d.setColor(COR_TEXTO_PRETO);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("m³", odometroX + odometroWidth + 10, odometroY + odometroHeight - 15);
    }

    private static void desenharRelogios(Graphics2D g2d, Point center, String label) {
        int raioRelogio = 40;
        g2d.setColor(COR_TEXTO_PRETO);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(center.x - raioRelogio, center.y - raioRelogio, raioRelogio * 2, raioRelogio * 2);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i < 10; i++) {
            double angulo = Math.toRadians(i * 36 - 90); // 360/10 = 36 graus por número
            int xNum = (int) (center.x + (raioRelogio - 10) * Math.cos(angulo));
            int yNum = (int) (center.y + (raioRelogio - 10) * Math.sin(angulo));
            
            int xMarcacao = (int) (center.x + (raioRelogio - 2) * Math.cos(angulo));
            int yMarcacao = (int) (center.y + (raioRelogio - 2) * Math.sin(angulo));
            
            g2d.drawLine(xMarcacao, yMarcacao, center.x + (int)((raioRelogio) * Math.cos(angulo)), center.y + (int)((raioRelogio) * Math.sin(angulo)));
            
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(String.valueOf(i), xNum - fm.stringWidth(String.valueOf(i))/2, yNum + fm.getAscent()/2);
        }
    }

    private static void desenharPonteiro(Graphics2D g2d, Point center, char posicao, int tamanho) {
        int valor = Character.getNumericValue(posicao);
        
        if (valor < 0 || valor > 9) {
            System.err.println("Aviso: Posição do ponteiro inválida recebida: '" + posicao + "' (código: " + (int)posicao + "). Ponteiro não será desenhado.");
            return; // Posição inválida, não desenha o ponteiro
        }

        double angulo = Math.toRadians(valor * 36 - 90);
        
        AffineTransform oldTransform = g2d.getTransform();
        try {
            g2d.translate(center.x, center.y);
            g2d.rotate(angulo);
            
            g2d.setColor(COR_PONTEIRO_VERMELHO);
            g2d.setStroke(new BasicStroke(2));
            
            Polygon ponteiro = new Polygon();
            ponteiro.addPoint(0, 0);
            ponteiro.addPoint(-4, 10);
            ponteiro.addPoint(0, -tamanho);
            ponteiro.addPoint(4, 10);
            g2d.fill(ponteiro);
        } finally {
            g2d.setTransform(oldTransform);
        }
    }
}