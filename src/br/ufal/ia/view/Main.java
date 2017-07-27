package br.ufal.ia.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import br.ufal.ia.bo.Interpreter;
import br.ufal.ia.bo.NotFoundException;

public class Main extends JFrame implements ActionListener{

	private JTextArea txtsource, txtresult;
	private JButton btnRun, btnImplica;
	public Main(){
		super("Modus Ponens");
		init();
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		txtsource.requestFocusInWindow();
	}
	
	private void init(){
		getContentPane().setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel pnButtons = new JPanel();
		txtsource = new JTextArea(30, 70);
		txtresult = new JTextArea(10, 70);
		txtresult.setEditable(false);
		
		btnRun = new JButton(new ActionRun("Rodar", new ImageIcon("img/start.png"), "Analisar premissas", new Integer(KeyEvent.VK_R)));
		btnImplica = new JButton("\u2192");
		
		btnImplica.addActionListener(this);
		
		pnButtons.add(btnRun);
		pnButtons.add(btnImplica);
		
		sp.add(txtsource);
		sp.add(txtresult);
		add(sp, BorderLayout.CENTER);
		add(pnButtons, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		txtsource.append(((JButton)e.getSource()).getText());
		txtsource.requestFocusInWindow();
	}
	
	private class ActionRun extends AbstractAction{
		public ActionRun(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			txtresult.setText("");
			if (!txtsource.getText().trim().isEmpty())
				try {
					String res = Interpreter.translate(txtsource.getText());
					txtresult.append(res.length()==0 ? "Resposta inconclusiva!":res);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			txtsource.requestFocusInWindow();
		}
	}
}
