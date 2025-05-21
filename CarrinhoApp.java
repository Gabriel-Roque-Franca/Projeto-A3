import javax.swing.*; //Classes para criar a interface gráfica (botões, janelas, etc.).
import javax.swing.border.EmptyBorder; // Para adicionar margens vazias em componentes.
import java.awt.*; // Classes básicas de layout e cores (BorderLayout, GridBagLayout, etc.).
import java.text.DecimalFormat; // Formata números em formato monetário (ex: R$ 20,00).
import java.awt.event.ActionEvent; // Para lidar com eventos de botões.
import java.awt.event.ActionListener; // Para lidar com eventos de botões.


// Classe principal que representa o carrinho de compras.
public class CarrinhoApp {
    // Atributos da classe
    private JFrame frame; // Janela principal da aplicação.
    private JPanel cartItemsPanel; // Painel que contém os itens do carrinho.
    private JLabel totalLabel; // Rótulo que exibe o total do carrinho.
    private DecimalFormat currencyFormat = new DecimalFormat("R$ #,##0.00"); // Formata valores em moeda
    private double currentTotal = 0.0; //Armazena o total acumulado dos itens

    // Construtor da classe CarrinhoApp
    public CarrinhoApp() {
        frame = new JFrame("Carrinho de Compras"); // Cria a janela principal com o título "Carrinho de Compras".
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela ao clicar no "X".
        frame.setSize(900, 700); // Define o tamanho da janela.
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela.

        // Painel principal que contém todos os componentes da interface.
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Define o layout do painel principal como BorderLayout com espaçamento de 10 pixels.
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); //EmptyBorderadiciona margem de 10 pixels em todos os lados
        mainPanel.add(createTopBar(), BorderLayout.NORTH); // createToBar adiciona o cabeçalho no topo
        mainPanel.add(createCenterContainer(), BorderLayout.CENTER); // createCenterContainer adiciona o painel central
        mainPanel.add(createFooter(), BorderLayout.SOUTH); // createFooter adiciona o rodapé com o total e botões

        //Exibição da janela      
        //Adiciona o mainPanel ao freame e torne a janela visível
        frame.add(mainPanel);
        frame.setVisible(true);

        // Adição de intens ao carrinho contendo o nome, tamanho, quantidade e valor unitário
        addCartItem("PRODUTO 01", "M", 2, 20.00);
        addCartItem("PRODUTO 02", "P", 1, 19.99);
        addCartItem("PRODUTO 03", "G", 1, 25.00);
    }

    // Métodos creteToBar para criar os componentes da interface gráfica (Cabeçalho)
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout()); // topBar é o painel com botão Voltar (esquerda), título (centro) e botão Continuar Compra (direita)

        JLabel title = new JLabel("MEU CARRINHO", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        topBar.add(title, BorderLayout.CENTER);

        JButton continuarBtn = new JButton("Continuar Compra");
        continuarBtn.addActionListener(e -> showMsg("Botão de Continuar Compra Clicado")); // showMsg exibe uma mensagem quando o botão é clicado
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.add(continuarBtn);
        topBar.add(right, BorderLayout.EAST);

        return topBar;
    }

    // Método createCenterContainer cria o painel central que contém os detalhes do pedido e os itens do carrinho
    private JPanel createCenterContainer() {
        JPanel center = new JPanel(new BorderLayout());
        center.add(createOrderDetailsPanel(), BorderLayout.NORTH); // createOrderDetailsPanel adiciona os detalhes do pedido no topo "Endereço" e "Telefone"

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(new Color(240, 240, 240));
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // cartItemsPanel é o painel que contém os itens do carrinho

        JScrollPane scroll = new JScrollPane(cartItemsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        center.add(scroll, BorderLayout.CENTER);

        return center;
    }

    // Método createOrderDetailsPanel cria o painel com os detalhes do pedido (Endereço e Telefone)
    private JPanel createOrderDetailsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelField(panel, gbc, 0, "Endereço:", 20);
        addLabelField(panel, gbc, 2, "Telefone:", 15);

        return panel;
    }

    // Método addLabelField adiciona um rótulo e um campo de texto ao painel
    private void addLabelField(JPanel panel, GridBagConstraints gbc, int x, String label, int size) {
        gbc.gridx = x;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = x + 1;
        gbc.weightx = 1.0;
        panel.add(new JTextField(size), gbc);
        gbc.weightx = 0;
    }

    // Método createFooter cria o rodapé com o total do carrinho e botões "Adicionar Item" e "Finalizar Pedido"
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(new EmptyBorder(10, 0, 0, 0));

        totalLabel = new JLabel("Total do Carrinho: " + currencyFormat.format(currentTotal), SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        footer.add(totalLabel, BorderLayout.EAST);

        // Painel com botões "Adicionar Item" e "Finalizar Pedido"
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton finalizar = new JButton("Finalizar Pedido");
        finalizar.addActionListener(e -> showMsg("Botão Finalizar Pedido clicado!"));
        buttons.add(finalizar);

        footer.add(buttons, BorderLayout.SOUTH);
        return footer;
    }

    // Método addCartItem adiciona um item ao carrinho com nome, tamanho, quantidade e valor unitário e um painel para cada item
    private void addCartItem(String name, String size, int quantity, double unitValue) {
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        itemPanel.add(new JCheckBox(), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1; gbc.anchor = GridBagConstraints.WEST;
        itemPanel.add(new JLabel(name), gbc);
        gbc.gridy = 1;
        itemPanel.add(new JLabel("Tamanho: " + size), gbc);

        // --- Adição dos botões de quantidade e do rótulo de quantidade ---
        JPanel quantityControls = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        quantityControls.setBackground(Color.WHITE);

        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel(String.valueOf(quantity));
        JButton plusButton = new JButton("+");

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentQuantity = Integer.parseInt(quantityLabel.getText());
                if (currentQuantity > 1) {
                    currentQuantity--;
                    quantityLabel.setText(String.valueOf(currentQuantity));
                    // Atualiza o valor total do item e o total geral
                    currentTotal -= unitValue;
                    updateItemAndTotalLabels(itemPanel, currentQuantity, unitValue);
                } else if (currentQuantity == 1) {
                    int resposta = JOptionPane.showConfirmDialog(frame, "Deseja remover o item " + name + " do carrinho?", "Remover Item", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        currentTotal -= unitValue;
                        updateTotalLabel();
                        cartItemsPanel.remove(itemPanel);
                        cartItemsPanel.revalidate();
                        cartItemsPanel.repaint();
                        showMsg(name + " removido com sucesso");
                    }
                }
            }
        });

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentQuantity = Integer.parseInt(quantityLabel.getText());
                currentQuantity++;
                quantityLabel.setText(String.valueOf(currentQuantity));
                // Atualiza o valor total do item e o total geral
                currentTotal += unitValue;
                updateItemAndTotalLabels(itemPanel, currentQuantity, unitValue);
            }
        });

        quantityControls.add(minusButton);
        quantityControls.add(quantityLabel);
        quantityControls.add(plusButton);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2; gbc.anchor = GridBagConstraints.CENTER;
        itemPanel.add(quantityControls, gbc);
        // --- Fim da adição dos botões de quantidade ---

        gbc.gridx = 3; gbc.gridy = 0; gbc.gridheight = 2; gbc.anchor = GridBagConstraints.EAST;
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        actions.setBackground(Color.WHITE);
        //Botão remove o item do carrinho e atualiza o total
        JButton del = new JButton("Remover");
        del.addActionListener(e -> {
            // Caixa de diálogo de confirmação de remoção do item
            int resposta = JOptionPane.showConfirmDialog(frame, "Deseja remover o item " + name + " do carrinho?", "Remover Item", JOptionPane.YES_NO_OPTION);
            // Se o usuário clicar em Sim (YES_OPTION), remove o item do carrinho
            if (resposta == JOptionPane.YES_OPTION) {
                // Ao remover, pegamos a quantidade atual do item para subtrair corretamente do total
                int currentQuantity = Integer.parseInt(quantityLabel.getText());
                currentTotal -= currentQuantity * unitValue;
                updateTotalLabel();
                cartItemsPanel.remove(itemPanel);
                cartItemsPanel.revalidate();
                cartItemsPanel.repaint();
                showMsg(name + " removido com sucesso");
            }
        });
        actions.add(del);
        itemPanel.add(actions, gbc);

        // Modificando a exibição de preços para ser atualizável
        // Primeiro, remova o painel de preços antigo se houver
        // (Isso é uma simplificação, em uma aplicação mais complexa, você poderia ter uma referência direta ao JLabel)
        // Para este exemplo, vamos recriá-lo ou atualizar seus componentes.
        // A melhor prática seria ter referências diretas aos JLabels de quantidade e valor total do item.

        // Para simplificar, vou criar um novo JPanel de preços e o método updateItemAndTotalLabels irá atualizá-lo
        JPanel prices = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        prices.setBackground(Color.WHITE);
        JLabel quantityDisplayLabel = new JLabel("Quantidade: " + quantity); // Nova referência
        JLabel itemValueLabel = new JLabel("Valor: " + currencyFormat.format(unitValue)); // Nova referência
        JLabel itemTotalPriceLabel = new JLabel("Valor Total: " + currencyFormat.format(quantity * unitValue)); // Nova referência

        prices.add(quantityDisplayLabel);
        prices.add(itemValueLabel);
        prices.add(itemTotalPriceLabel);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; // Ajuste o gridwidth para cobrir todos os itens
        gbc.anchor = GridBagConstraints.EAST;
        itemPanel.add(prices, gbc);


        cartItemsPanel.add(itemPanel);
        currentTotal += quantity * unitValue;
        updateTotalLabel(); // Atualiza o total do carrinho
    }

    // --- Novo método para atualizar o rótulo de quantidade e o valor total do item ---
    private void updateItemAndTotalLabels(JPanel itemPanel, int newQuantity, double unitValue) {
        // Encontra os JLabels dentro do itemPanel para atualizar
        Component[] components = itemPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel subPanel = (JPanel) comp;
                if (subPanel.getLayout() instanceof FlowLayout) { // Verifica se é o painel de preços
                    for (Component priceComp : subPanel.getComponents()) {
                        if (priceComp instanceof JLabel) {
                            JLabel label = (JLabel) priceComp;
                            if (label.getText().startsWith("Quantidade:")) {
                                label.setText("Quantidade: " + newQuantity);
                            } else if (label.getText().startsWith("Valor Total:")) {
                                label.setText("Valor Total: " + currencyFormat.format(newQuantity * unitValue));
                            }
                        }
                    }
                }
            }
        }
        updateTotalLabel(); // Garante que o total geral seja atualizado
    }


    // Métodos auxiliares
    private void updateTotalLabel() { //Atualiza o valor total exibido
        totalLabel.setText("Total do Carrinho: " + currencyFormat.format(currentTotal));
    }

    private void showMsg(String msg) { //Exibe uma mensagem em uma janela de diálogo
        JOptionPane.showMessageDialog(frame, msg);
    }

    // Método main para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarrinhoApp::new);
    }
}
