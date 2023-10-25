




 package veterinaria;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private Image backgroundImage;

    public CustomPanel() {
        // Carga la imagen de fondo desde un archivo
        String imagePath = "/veterinaria/Imagenes/fondoVete.png";
        backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen de fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

