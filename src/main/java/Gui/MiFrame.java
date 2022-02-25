package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andres twitter andreescondo
 */
public class MiFrame extends JFrame {

    private JPanel contentPane;
    private JPanel panelSur;
    private JPanel panelNorte;
    private JPanel panelCenter;
    private JPanel panelContentNorte;
    private JButton btnOk;
    private JLabel labelNorte;
    private JLabel labelContent;
    private JButton buttonContent;
    private JTextField textContent;

    /**
     * *
     * 0 = RED 0 = GREEN 0 = BLACK Number = ALPHA our transparent level
     */
    private Color colorBackgroundRGBA = new Color(0, 0, 0, 205);
    private Color colorButton = new Color(255, 255, 255);
    private Color transparentColor = new Color(255, 255, 255, 100);
    private Color transparentColor2 = new Color(255, 255, 255, 150);
    //private Color colorBorder = new Color(255, 0, 0, 250);

    private Color colorText = new Color(0, 155, 0, 250);
    private Color colorBlue = new Color(0, 0, 255, 250);

    private int numberInput;
    private int numberAditional = 0;

    public MiFrame() {
        //Inicializacion del frama con el titulo por defecto
        super("Andres Condo");
        showDialog();
        initComponents();

    }

    private void initComponents() {
        try {
            //estabelece las medidas de la pantalla
            setSize(800, 400);

            //centrar el contenedor de manera default
            setLocationRelativeTo(null);

            //cierra el proceso del programa al cerrar la ventana
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Creación de ContentPane
            contentPane = new JPanel(new BorderLayout());
            setContentPane(contentPane);

            //Creacion de JLabel
            labelNorte = new JLabel("Valor ingresado: " + numberInput);
            labelNorte.setHorizontalAlignment(HEIGHT);
            btnOk = new JButton("");

            //creacion de formato gridLayout para posterior uso
            GridLayout miLayout = new GridLayout(3, 3);

            //creacion de contentPane secundario
            panelSur = new JPanel();
            panelCenter = new JPanel(miLayout);
            panelNorte = new JPanel();
            panelContentNorte = new JPanel();
            

            // == Asignacion de contenedores secundarios
            contentPane.add(panelSur, BorderLayout.SOUTH);
            contentPane.add(panelNorte, BorderLayout.NORTH);
            panelNorte.add(panelContentNorte);
            panelContentNorte.add(labelNorte);
            contentPane.add(panelCenter, BorderLayout.CENTER);
            panelSur.add(btnOk);

            /**
             * *
             * metodo para validar el numero de elementos a crear
             *
             * @param numberInput valor ingresado por el showDialog
             * @return valor de la division entre la cantidad maxima de
             * elementos: * label * button * textArea
             *
             */
            int number = validateNumber(numberInput);

            //creacion de elementos correspondientes al numero ingresado
            for (int i = 0; i < number; i++) {
                labelContent = new JLabel("N°: " + generateNumberRandom());
                labelContent.setForeground(colorText);
                labelContent.setBackground(colorBackgroundRGBA);
                buttonContent = new JButton("N°: " + generateNumberRandom());
                textContent = new JTextField("");
                textContent.setEnabled(false);

                panelCenter.add(labelContent);
                panelCenter.add(buttonContent);
                panelCenter.add(textContent);

            }

            // si el numero tiene excedentes del multiplo de tres, dependiendo de la cantidad
            //se pintaran los elemento en pantalla
            if (numberAditional != 0) {
                if (numberAditional == 1) {
                    labelContent = new JLabel("N°: " + generateNumberRandom());
                    labelContent.setForeground(colorText);
                    labelContent.setBackground(colorBackgroundRGBA);
                    panelCenter.add(labelContent);
                }
                if (numberAditional == 2) {
                    labelContent = new JLabel("N°: " + generateNumberRandom());
                    labelContent.setForeground(colorText);
                    labelContent.setBackground(colorText);

                    buttonContent = new JButton("" + generateNumberRandom());

                    panelCenter.add(labelContent);
                    panelCenter.add(buttonContent);

                }
            }

            //Asignacion de colores
            //contentPane.setBackground(colorBlue);
            btnOk.setBackground(colorButton);
            panelSur.setBackground(transparentColor);
            panelCenter.setBackground(colorBackgroundRGBA);
            
            
        } catch (Exception e) {
            System.out.println("El numero que ingreso es probablemente muy alto intente nuevamente con otro");

        }
    }
    


    /**
     * genera un numero aleatorio entre el 1 y el 100
     *
     * @return retorna un numero aleatorio entre el 1 y el 100
     *
     */
    private int generateNumberRandom() {
        int Number;
        int min = 1;
        int max = 100;

        Random random = new Random();

        Number = random.nextInt(max + min) + min;

        return Number;
    }

    /**
     * *
     * Validacion del numero ingresado sea multiplo de 3 ya que esa es la
     * cantidad de elementos a crear si es multiplo de tres regresa el valor de
     * esa division si no es multiplo de tres retorna
     *
     * @param number numero ingresado por el showDialog
     * @return newNumber valor de la division
     */
    private int validateNumber(int number) {
        System.out.println(number);
        int newNumber;
        if (number % 3 == 0) {
            newNumber = number / 3;
            System.out.println("Es divisible");
            System.out.println(newNumber);

            return newNumber;

        } else {
            newNumber = number / 3;
            int residuo = number % 3;
            System.out.println("NO Es divisible");
            System.out.println(newNumber);
            setNumberAditional(residuo);
            return newNumber;
        }
    }

    /**
     * *
     * Metodo encargado de controlar el showDialog emergente, su ejecucion va
     * antes de la ejecucion del panel principal
     *
     */
    private void showDialog() {
        try {
            boolean flag;
            //creacion de showDialog
            String number = JOptionPane.showInputDialog(panelCenter,
                    "Ingrese un número entero",
                    "Ingreso de datos",
                    JOptionPane.INFORMATION_MESSAGE);

            //uso de expresiones regulares o regex para validar si la cadena entera solo posee numeros
            flag = number.matches("[+-]?\\d*(\\.\\d+)?");

            /**
             * *
             * Entra a un validacion si es falso se genera un funcion recursiva
             * asi misma si es verdadero se envia el valor ingresado al metodo
             * setNumberInput, convirtiendose en entero
             *
             * si posee una coma o un punto (simbolizando un decimal) se valida
             * como falso
             *
             */
            if (flag == false || number.contains(".") || number.contains(",")) {
                //generacion de funcion recursiva
                showDialog();
            } else {
                /**
                 * Se envia el valor ingresado del showDialog al metodo
                 * setNumbreInput, pero antes se convierte en un número entero
                 *
                 * @params Integer.parseInt(number) se envia la cadena
                 * convirtiendola en un entero
                 */
                setNumberInput(Integer.parseInt(number));
            }
        } catch (Exception e) {
            showDialog();
        }

    }

    public int getNumberInput() {
        return numberInput;
    }

    public void setNumberInput(int numberInput) {
        this.numberInput = numberInput;
    }

    public int getNumberAditional() {
        return numberAditional;
    }

    public void setNumberAditional(int numberAditional) {
        this.numberAditional = numberAditional;
    }
}
