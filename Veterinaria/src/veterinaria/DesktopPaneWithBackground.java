package veterinaria;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

// Clase que extiende JDesktopPane y permite establecer una imagen de fondo para el panel
public class DesktopPaneWithBackground extends JDesktopPane {

    private final Image backgroundImage; // Almacena la imagen de fondo

    // Constructor que toma la ruta de la imagen como argumento y carga la imagen
    public DesktopPaneWithBackground(String imagePath) {
        // Cargar la imagen de fondo utilizando la ruta proporcionada
        backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    // Sobrescribe el método paintComponent para dibujar la imagen de fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método paintComponent de la clase base
        // Dibuja la imagen de fondo en las coordenadas (0, 0) con el ancho y alto del panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
