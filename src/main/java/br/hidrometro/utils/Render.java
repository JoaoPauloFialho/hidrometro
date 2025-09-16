package main.java.br.hidrometro.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;
import javax.imageio.ImageIO;

public class Render {

    public static void gerarImagem(float volume, int posicaoLitros, int posicaoDecilitros, String fileName, String dir) {
        int width = 800;
        int height = 500;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 70, 140));
        g.fillRect(0, 0, width, height);

        int circleSize = 400;
        int circleX = (width - circleSize) / 2;
        int circleY = (height - circleSize) / 2;
        g.setColor(Color.WHITE);
        g.fillOval(circleX, circleY, circleSize, circleSize);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawOval(circleX, circleY, circleSize, circleSize);

        g.setFont(new Font("Monospaced", Font.BOLD, 40));

        String volumeStr = String.format(Locale.US, "%06.2f", volume);
        String[] partes = volumeStr.split("\\.");
        String parteInteira = partes[0];
        String parteDecimal = partes[1];

        int visorX = width / 2 - 150;
        int visorY = height / 2 - 100;

        for (int i = 0; i < parteInteira.length(); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(visorX + i * 45, visorY, 40, 50);
            g.setColor(Color.BLACK);
            g.drawRect(visorX + i * 45, visorY, 40, 50);
            g.drawString("" + parteInteira.charAt(i), visorX + i * 45 + 10, visorY + 40);
        }

        g.setColor(Color.RED);
        g.fillRect(visorX + parteInteira.length() * 45, visorY, 40, 50);
        g.setColor(Color.WHITE);
        g.drawRect(visorX + parteInteira.length() * 45, visorY, 40, 50);
        g.drawString("" + parteDecimal.charAt(0), visorX + parteInteira.length() * 45 + 10, visorY + 40);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("m³", visorX + parteInteira.length() * 45 + 50, visorY + 35);

        desenharRelogio(g, width/2 - 80, height/2 + 70, "Decilitros", posicaoDecilitros);
        desenharRelogio(g, width/2 + 80, height/2 + 70, "Litros", posicaoLitros);

        g.dispose();

        try {
            File pasta = new File(dir);
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            File arquivoSaida = new File(pasta, fileName.endsWith(".jpeg") ? fileName : fileName + ".jpeg");

            ImageIO.write(image, "jpg", arquivoSaida);
            System.out.println("Imagem gerada em: " + arquivoSaida.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void desenharRelogio(Graphics2D g, int centerX, int centerY, String label, int posicao) {
        int radius = 70;
        g.setColor(Color.WHITE);
        g.fillOval(centerX - radius, centerY - radius, 2*radius, 2*radius);
        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, 2*radius, 2*radius);

        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(90 - i * 36); // 36° cada passo
            int x = (int) (centerX + Math.cos(angle) * (radius - 20));
            int y = (int) (centerY - Math.sin(angle) * (radius - 20));
            g.drawString(String.valueOf(i), x - 5, y + 5);
        }

        double anguloPonteiro = Math.toRadians(90 - posicao * 36);
        int ponteiroX = (int) (centerX + Math.cos(anguloPonteiro) * (radius - 25));
        int ponteiroY = (int) (centerY - Math.sin(anguloPonteiro) * (radius - 25));
        g.setStroke(new BasicStroke(3));
        g.drawLine(centerX, centerY, ponteiroX, ponteiroY);

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString(label, centerX - 30, centerY + radius + 20);
    }
}
