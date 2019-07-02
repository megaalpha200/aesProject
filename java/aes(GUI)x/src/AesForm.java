import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import aesfunctions.AesMain;
import aesfunctions.AesMainFunctions;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import static aesfunctions.AesMainFunctions.CipherMode.DECRYPT;
import static aesfunctions.AesMainFunctions.CipherMode.ENCRYPT;
import static aesfunctions.AesMainFunctions.TextMode.HEX;
import static aesfunctions.AesMainFunctions.TextMode.STRING;
import static aesfunctions.AesMainFunctions.debugOutput;

public class AesForm {
    private JPanel aesPanel;
    private JRadioButton inputTextStrRadioButton;
    private JRadioButton inputTextHexRadioButton;
    private JTextArea inputTextTextField;
    private JTextField keyTextField;
    private JRadioButton keyStrRadioButton;
    private JRadioButton keyHexRadioButton;
    private JButton executeButton;
    private JTextField outputTextHexTextField;
    private JTextArea outputTextStrTextField;
    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private JLabel titleLabel;
    private JLabel inputTextLabel;
    private JLabel keyLabel;
    private JLabel outputTextHexLabel;
    private JLabel outputTextStrLabel;
    private JTextArea debugTextArea;
    private JLabel debugLabel;
    private JButton outputTextHexCopyButton;
    private JButton outputTextStrCopyButton;

    public AesForm() {

        executeButton.addActionListener(e -> {
            try {
                String inputText = inputTextTextField.getText();
                String inputKey = keyTextField.getText();
                AesMainFunctions.TextMode textModeInput = (inputTextStrRadioButton.isSelected()) ? STRING : HEX;
                AesMainFunctions.TextMode textModeKey = (keyStrRadioButton.isSelected()) ? STRING : HEX;
                AesMainFunctions.CipherMode cipherMode = (encryptRadioButton.isSelected()) ? ENCRYPT : DECRYPT;

                String inputTextBin = AesMain.encryptDecryptInputTextPrep(inputText, cipherMode, textModeInput);
                String keyTextBin = AesMain.keyTextPrep(inputKey, textModeKey);

                String completionMessage = "";
                Pair<String, String> outputTextHexStringPair = null;
                switch (cipherMode) {
                    case ENCRYPT:
                        outputTextHexStringPair = AesMain.encrypt(inputTextBin, keyTextBin);
                        completionMessage = "The PlainText has been sucessfully Encrypted!";
                        break;
                    case DECRYPT:
                        outputTextHexStringPair = AesMain.decrypt(inputTextBin, keyTextBin);
                        completionMessage = "The CipherText has been sucessfully Decrypted!";
                        break;
                    default:
                        throw new Exception("An unexpected error has occured!");
                }

                if (outputTextHexStringPair == null || outputTextHexStringPair.getKey().equals(""))
                    throw new Exception("Please enter inputs!");

                String outputTextHex = outputTextHexStringPair.getKey();
                String outputTextStr = outputTextHexStringPair.getValue();
                outputTextHexTextField.setText(outputTextHex);
                outputTextStrTextField.setText(outputTextStr);

                String debugStr = debugOutput.toString();
                debugTextArea.setText(debugStr);
                debugOutput = new StringBuilder();

                JOptionPane.showMessageDialog(null, completionMessage, "Complete!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });
        outputTextHexCopyButton.addActionListener(e -> {
            String outputHexStr = outputTextHexTextField.getText();
            StringSelection stringSelection = new StringSelection(outputHexStr);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE);
        });
        outputTextStrCopyButton.addActionListener(e -> {
            String outputStr = outputTextStrTextField.getText();
            StringSelection stringSelection = new StringSelection(outputStr);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AES");
        frame.setContentPane(new AesForm().aesPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        aesPanel = new JPanel();
        aesPanel.setLayout(new GridLayoutManager(10, 6, new Insets(0, 0, 0, 0), -1, -1));
        aesPanel.setMaximumSize(new Dimension(800, 500));
        aesPanel.setMinimumSize(new Dimension(800, 500));
        aesPanel.setPreferredSize(new Dimension(800, 500));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 16, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setText("AES Encryptor/Decryptor using Java");
        aesPanel.add(titleLabel, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputTextLabel = new JLabel();
        inputTextLabel.setText("Input:");
        aesPanel.add(inputTextLabel, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(31, 46), null, 1, false));
        inputTextStrRadioButton = new JRadioButton();
        inputTextStrRadioButton.setSelected(true);
        inputTextStrRadioButton.setText("String");
        aesPanel.add(inputTextStrRadioButton, new GridConstraints(1, 4, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(54, 46), null, 0, false));
        inputTextHexRadioButton = new JRadioButton();
        inputTextHexRadioButton.setText("Hexadecimal");
        aesPanel.add(inputTextHexRadioButton, new GridConstraints(1, 5, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(91, 46), null, 0, false));
        keyLabel = new JLabel();
        keyLabel.setText("Key:");
        aesPanel.add(keyLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        keyTextField = new JTextField();
        aesPanel.add(keyTextField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(350, -1), null, 0, false));
        keyStrRadioButton = new JRadioButton();
        keyStrRadioButton.setSelected(true);
        keyStrRadioButton.setText("String");
        aesPanel.add(keyStrRadioButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        keyHexRadioButton = new JRadioButton();
        keyHexRadioButton.setText("Hexadecimal");
        aesPanel.add(keyHexRadioButton, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        executeButton = new JButton();
        executeButton.setText("Execute");
        aesPanel.add(executeButton, new GridConstraints(5, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextHexLabel = new JLabel();
        outputTextHexLabel.setText("Output Text (Hex):");
        aesPanel.add(outputTextHexLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        outputTextStrLabel = new JLabel();
        outputTextStrLabel.setText("Output Text (String):");
        aesPanel.add(outputTextStrLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        outputTextHexTextField = new JTextField();
        outputTextHexTextField.setBackground(new Color(-1));
        outputTextHexTextField.setEditable(false);
        outputTextHexTextField.setEnabled(true);
        aesPanel.add(outputTextHexTextField, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        debugLabel = new JLabel();
        debugLabel.setText("Debug:");
        aesPanel.add(debugLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        encryptRadioButton = new JRadioButton();
        encryptRadioButton.setSelected(true);
        encryptRadioButton.setText("Encrypt");
        aesPanel.add(encryptRadioButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decryptRadioButton = new JRadioButton();
        decryptRadioButton.setText("Decrypt");
        aesPanel.add(decryptRadioButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextHexCopyButton = new JButton();
        outputTextHexCopyButton.setText("Copy");
        aesPanel.add(outputTextHexCopyButton, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextStrCopyButton = new JButton();
        outputTextStrCopyButton.setText("Copy");
        aesPanel.add(outputTextStrCopyButton, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        aesPanel.add(scrollPane1, new GridConstraints(9, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 300), null, 0, false));
        debugTextArea = new JTextArea();
        debugTextArea.setEditable(false);
        scrollPane1.setViewportView(debugTextArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        aesPanel.add(scrollPane2, new GridConstraints(1, 1, 2, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(350, 46), null, 0, false));
        inputTextTextField = new JTextArea();
        scrollPane2.setViewportView(inputTextTextField);
        final JScrollPane scrollPane3 = new JScrollPane();
        aesPanel.add(scrollPane3, new GridConstraints(7, 1, 1, 4, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(350, 20), null, 0, false));
        outputTextStrTextField = new JTextArea();
        outputTextStrTextField.setBackground(new Color(-1));
        outputTextStrTextField.setEditable(false);
        scrollPane3.setViewportView(outputTextStrTextField);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(inputTextStrRadioButton);
        buttonGroup.add(inputTextHexRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(keyStrRadioButton);
        buttonGroup.add(keyHexRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(encryptRadioButton);
        buttonGroup.add(decryptRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return aesPanel;
    }

}
