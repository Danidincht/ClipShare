
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danid
 */
public class Servidor extends javax.swing.JFrame {
    ServerSocket server;
    Socket conexion;
    
    ObjectInputStream /*oisServer,*/ oisCliente;
    ArrayList<ObjectInputStream> oisServer;
    ObjectOutputStream /*oosServer,*/ oosCliente;
    ArrayList<ObjectOutputStream> oosServer;
    ArrayList<String> clientes;
    
    FileOutputStream fosHistorial;
    
    Thread hiloInput, hiloOutput, serverThread, multiServerThread;
    
    DefaultTableModel sentModel, recibidosModel;
    File historial;
    Message lastMsg = new Message();
    
    String id="id";
    int maxConexiones, clientesConectados;
    /**
     * Creates new form Ventana
     */
    public Servidor() {
        initComponents();
        
        sentModel = new DefaultTableModel(new String[]{"Enviado"}, 0);
        sentTabla.setModel(sentModel);
        
        recibidosModel = new DefaultTableModel(new String[]{"Recibido"}, 0);
        recibidosTabla.setModel(recibidosModel);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        puertoDialog = new javax.swing.JDialog();
        label1 = new java.awt.Label();
        puertoTf = new javax.swing.JTextField();
        aceptarPuertoBt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ipTf = new javax.swing.JTextField();
        serverSl = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        maxConexSp = new javax.swing.JSpinner();
        enviarBt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sentTabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        recibidosTabla = new javax.swing.JTable();
        sentCopiarBt = new javax.swing.JButton();
        recibidosCopiarBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgTa = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nuevaMenu = new javax.swing.JMenuItem();
        salirMenu = new javax.swing.JMenuItem();

        label1.setText("Puerto:");

        aceptarPuertoBt.setText("Aceptar");
        aceptarPuertoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarPuertoBtActionPerformed(evt);
            }
        });

        jLabel1.setText("Direccion:");

        serverSl.setMaximum(1);
        serverSl.setValue(0);

        jLabel2.setText("Conectar como cliente");

        jLabel3.setText("Iniciar servidor");

        jLabel4.setText("Num max conexiones");

        maxConexSp.setModel(new javax.swing.SpinnerNumberModel());
        maxConexSp.setEditor(new javax.swing.JSpinner.NumberEditor(maxConexSp, ""));

        javax.swing.GroupLayout puertoDialogLayout = new javax.swing.GroupLayout(puertoDialog.getContentPane());
        puertoDialog.getContentPane().setLayout(puertoDialogLayout);
        puertoDialogLayout.setHorizontalGroup(
            puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(puertoDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, puertoDialogLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(maxConexSp))
                    .addGroup(puertoDialogLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(serverSl, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(puertoDialogLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ipTf, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(puertoDialogLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(puertoDialogLayout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(puertoTf, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, puertoDialogLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(aceptarPuertoBt)))
                .addContainerGap())
        );
        puertoDialogLayout.setVerticalGroup(
            puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, puertoDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(ipTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(puertoTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(serverSl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(puertoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarPuertoBt)
                    .addComponent(jLabel4)
                    .addComponent(maxConexSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SocketServidor");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        enviarBt.setText("Enviar");
        enviarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarBtActionPerformed(evt);
            }
        });

        sentTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(sentTabla);

        recibidosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(recibidosTabla);

        sentCopiarBt.setText("Copiar enviado");
        sentCopiarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sentCopiarBtActionPerformed(evt);
            }
        });

        recibidosCopiarBt.setText("Copiar recibido");
        recibidosCopiarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibidosCopiarBtActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(msgTa);

        jMenu1.setText("File");

        nuevaMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        nuevaMenu.setText("Nueva conexion");
        nuevaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMenuActionPerformed(evt);
            }
        });
        jMenu1.add(nuevaMenu);

        salirMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        salirMenu.setText("Salir");
        salirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuActionPerformed(evt);
            }
        });
        jMenu1.add(salirMenu);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sentCopiarBt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recibidosCopiarBt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enviarBt)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sentCopiarBt)
                    .addComponent(recibidosCopiarBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(enviarBt))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarPuertoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarPuertoBtActionPerformed
        //Thread
        serverThread = new Thread(new MultiServerThread());
        serverThread.start();
        maxConexiones = Integer.valueOf(maxConexSp.getValue().toString());
    }//GEN-LAST:event_aceptarPuertoBtActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void enviarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarBtActionPerformed
        try
        {
            Message msgObj = new Message(Message.STRING_MESSAGE, id, msgTa.getText(), Calendar.getInstance().getTime());
            if(id.equals("Server"))
            {
                for(ObjectOutputStream oos: oosServer)
                {
                    hiloOutput = new Thread(new OutputThread(oos, msgObj));
                    hiloOutput.start();
                }
            }
            else
            {
                hiloOutput = new Thread(new OutputThread(oosCliente, msgObj));
                hiloOutput.start();
            }
        }
        catch(IOException e){e.printStackTrace();}
    }//GEN-LAST:event_enviarBtActionPerformed

    private void salirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuActionPerformed
        cerrar();
        System.exit(0);
    }//GEN-LAST:event_salirMenuActionPerformed

    private void nuevaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaMenuActionPerformed
        puertoDialog.setVisible(true);
        puertoDialog.pack();
        puertoDialog.setModal(true);
    }//GEN-LAST:event_nuevaMenuActionPerformed

    private void sentCopiarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sentCopiarBtActionPerformed
        if(sentTabla.getSelectedRow()!=-1)
        {
            String txt = sentTabla.getValueAt(sentTabla.getSelectedRow(), 0).toString();
            copiar(txt);
        }
        else
            JOptionPane.showMessageDialog(this, "No row selected");
    }//GEN-LAST:event_sentCopiarBtActionPerformed

    private void recibidosCopiarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibidosCopiarBtActionPerformed
        if(recibidosTabla.getSelectedRow()!=-1)
        {
            String txt = recibidosTabla.getValueAt(recibidosTabla.getSelectedRow(), 0).toString();
            copiar(txt);
        }
        else
            JOptionPane.showMessageDialog(this, "No row selected");
    }//GEN-LAST:event_recibidosCopiarBtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }

    class ServerThread implements Runnable
    {
        String puerto, date;
        Socket conexion;
        public ServerThread(Socket conexion, String puerto, String date)
        {
            this.conexion = conexion;
            this.puerto = puerto;
            this.date = date;
        }
        
        @Override
        public void run() {
            try
            {
                if(!conexion.isConnected())
                    return;
                
                System.out.println("Conectado");
                setTitle("ClipShare Server "/*@" + conexion.getInetAddress().toString().substring(1)*/);
                historial = new File("Historial_" + date + "_server.xml");
                
                crearHistorial();
                
                //Envio objectos Message
                ObjectOutputStream oos = new ObjectOutputStream(conexion.getOutputStream());
                oos.flush();
                oosServer.add(oos);
                ObjectInputStream ois = new ObjectInputStream(conexion.getInputStream());
                oisServer.add(ois);
                
                //Envio de ID a cliente
                String idCliente;
                while(clientes.contains(idCliente = generateId()));
                
                Message msgObj = new Message(Message.ID_MESSAGE, id, idCliente, Calendar.getInstance().getTime());
                clientes.add(idCliente);
                oos.writeObject(msgObj);
                
                Thread serverInput = new Thread(new InputThread(ois));

                serverInput.start();
            }
            catch(IOException e){e.printStackTrace();}
            catch(Exception e){e.printStackTrace();}
            serverThread = null;
        }
    }
    
    class MultiServerThread implements Runnable
    {
        @Override
        public void run() {
            try
            {
                puertoDialog.setVisible(false);
                String date = new SimpleDateFormat("ddMMyy_HHmm").format(Calendar.getInstance().getTime());

                String puerto = puertoTf.getText();
                if(serverSl.getValue()==1) //Servidor recibiendo clientes
                {
                    oosServer = new ArrayList<>();
                    oisServer = new ArrayList<>();
                    clientes = new ArrayList<>();
                    server = new ServerSocket(Integer.parseInt(puerto));

                    do
                    {
                        id="Server";
                        if(clientesConectados<maxConexiones || maxConexiones==0)
                        {
                            if(server.isClosed())
                                server = new ServerSocket(Integer.parseInt(puerto));
                            System.out.println("Aceptando clientes");
                            conexion = server.accept();
                            Thread input = new Thread(new ServerThread(conexion, puerto, date));
                            input.start();
                            clientesConectados++;
                        }
                        else
                            server.close();
//                        System.out.println("Clientes: " + clientes.size() + "/" + maxConexiones);
                    }while(id.equals("Server"));
                    System.out.println("Finalizado servidor");
                }
                else  //Cliente conectando a servidor
                {
                    System.out.println("Conectando a servidor");
                    setTitle("ClipShare Client (Conectando a " + ipTf.getText() + ")");
                    conexion = new Socket(ipTf.getText(),Integer.parseInt(puerto));
                    setTitle("ClipShare Client (Conectado a " + ipTf.getText() + ")");
                    historial = new File("Historial_" + date + "_client.xml");
                    
                    crearHistorial();
                    
                    oosCliente = new ObjectOutputStream(conexion.getOutputStream());
                    oosCliente.flush();
                    oisCliente = new ObjectInputStream(conexion.getInputStream());
                    
                    
                    hiloOutput = new Thread(new OutputThread(oosCliente, new Message()));
                    hiloInput = new Thread(new InputThread(oisCliente));
//                    hiloOutput.start();
                    hiloInput.start();
                }
            }
            catch(ConnectException e){JOptionPane.showMessageDialog(Servidor.this, "No se pudo conectar con el servidor"); setTitle("ClipShare");}
            catch(Exception e){e.printStackTrace();}
            
        }
    }
    
    class InputThread implements Runnable
    {
        //*Fake multi 
        ObjectInputStream ois;
        
        public InputThread(ObjectInputStream ois) throws IOException
        {
            this.ois = ois;
        }
        
        //Fake multi*//
        @Override
        public void run() {
            while(ois!=null/* || id.equals("Server")*/)
            {
                try
                {
                    System.out.println("Leyendo");
                    Message msgObj = (Message)ois.readObject();

                    if(msgObj.getType()==Message.ID_MESSAGE)
                    {
                        id=msgObj.text;
                        continue;
                    }
                    
                    recibidosModel.addRow(new String[]{msgObj.getText()});
                    synchronized(historial)
                    {
                        addMsg("Input", msgObj);
                    }
                }
                catch(EOFException e)
                {
                    ois = null;
                    System.out.println("Conexion terminada");
                    break;
                }
                catch(SocketException e)
                {
                    if(e.getMessage().equals("Connection reset"))
                    {
                        clientes.remove(oisServer.indexOf(ois));
                        oosServer.remove(oisServer.indexOf(ois));
                        oisServer.remove(ois);
                        System.out.println("Cliente desconectado");
                        clientesConectados--;
                        ois = null;
                    }
                    ois = null;
//                    else if(!e.getMessage().equals("Socket closed"))
//                        System.out.println(e.getMessage());
                }
                catch(ClassNotFoundException e){e.printStackTrace();}
                catch(IOException e)
                {
                    if(e.getMessage().equals("invalid type code: AC"))
                    {
                        
                    }
                    else
                        e.printStackTrace();
                }
                catch(Exception e){cerrar();}
            }
        }
    }
    
    class OutputThread implements Runnable
    {
        ObjectOutputStream oos;
        Message msgObj;
        
        public OutputThread(ObjectOutputStream oos, Message msgObj) throws IOException
        {
            this.msgObj = msgObj;
            this.oos = oos;
        }
        
        @Override
        public void run() {
            try
            {
//                Message msgObj = new Message(Message.STRING_MESSAGE, id, msgTa.getText(), Calendar.getInstance().getTime());
                
//                oosServer.writeObject(msgObj); //Simple connection
                oos.writeObject(msgObj);

                synchronized(historial)
                {
                    addMsg("Output", msgObj);
                }
                
                hiloOutput = null;
            }
            catch(SocketException e)
            {
                if(!e.getMessage().equals("Socket closed"))
                    e.printStackTrace();
            }
            catch(IOException e){e.printStackTrace();}
            catch(Exception e){cerrar();}
        }
    }

    public void copiar(String texto)
    {
        try
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clip = toolkit.getSystemClipboard();
            clip.setContents(new StringSelection(texto), new StringSelection(texto));
        }
        catch(Exception ex){ex.printStackTrace();}
    }
    
    public void crearHistorial()
    {
        try
        {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document historialDoc = db.newDocument();
            Element raiz = historialDoc.createElement("Historial");
            historialDoc.appendChild(raiz);
            
            Element outputElem = historialDoc.createElement("Output");
            raiz.appendChild(outputElem);
            
            Element inputElem = historialDoc.createElement("Input");
            raiz.appendChild(inputElem);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            Source source = new DOMSource(historialDoc);
            StreamResult result = new StreamResult(historial);
            
            transformer.transform(source, result);
        }
        catch(ParserConfigurationException e){}
        catch(TransformerConfigurationException e){}
        catch(TransformerException e){}
    }
    
    public void addMsg(String direccion, Message msg)
    {
        if(lastMsg.equals(msg) || direccion.equals("Input") && msg.getIdSender().equals(id)) //Reenvio de mensaje en servidor o recibo de mensaje enviado por el mismo cliente
            return;
        
        try
        {
            if(direccion.equals("Output"))
                sentModel.addRow(new String[]{msg.getText()});
            
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document historialDoc = db.parse(historial); 
            if(!historialDoc.hasChildNodes())
                return;
            
            NodeList output = historialDoc.getElementsByTagName(direccion);
            
            if(output!=null && output.getLength()>0)
            {
                Node outputNode = output.item(0);
                Element msgElem = historialDoc.createElement("msg");
                msgElem.setAttribute("id", msg.getIdSender());
                msgElem.setAttribute("time", new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(msg.getSentDate()));
                msgElem.setTextContent(msg.getText());
                outputNode.appendChild(msgElem);
            }
            else
                System.out.println("Nulo o vacio");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            Source source = new DOMSource(historialDoc);
            StreamResult result = new StreamResult(historial);
            
            transformer.transform(source, result);
            lastMsg = msg;
        }
        catch(Exception e){e.printStackTrace();}

    }
    
    public String generateId()
    {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.";
        String id = "";
        for(int i=0;i<5;i++)
        {
            char letra = chars.charAt((int)(Math.random()*chars.length()));
            id += letra;
        }
        return id;
    }
    
    public void cerrar()
    {
        try
        {
            hiloInput = null;
            for(ObjectInputStream ois : oisServer)
                ois.close();
            
            for(ObjectOutputStream oos: oosServer)
                oos.close();
            
            server.close();
            conexion.close();
        }
        catch(IOException e){System.out.println("Error cerrando"); return;}
        catch(NullPointerException e){}
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarPuertoBt;
    private javax.swing.JButton enviarBt;
    private javax.swing.JTextField ipTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JSpinner maxConexSp;
    private javax.swing.JTextArea msgTa;
    private javax.swing.JMenuItem nuevaMenu;
    private javax.swing.JDialog puertoDialog;
    private javax.swing.JTextField puertoTf;
    private javax.swing.JButton recibidosCopiarBt;
    private javax.swing.JTable recibidosTabla;
    private javax.swing.JMenuItem salirMenu;
    private javax.swing.JButton sentCopiarBt;
    private javax.swing.JTable sentTabla;
    private javax.swing.JSlider serverSl;
    // End of variables declaration//GEN-END:variables
}
