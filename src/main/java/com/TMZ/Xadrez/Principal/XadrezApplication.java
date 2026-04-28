package com.TMZ.Xadrez.Principal;

import com.TMZ.Xadrez.controller.GameController;
import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.PieceColor;
import com.TMZ.Xadrez.model.PieceType;
import com.TMZ.Xadrez.view.MainFrame;
import com.TMZ.Xadrez.view.SquareButton;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class XadrezApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(XadrezApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);

		SwingUtilities.invokeLater(() -> {
			MainFrame frame = new MainFrame();
			GameState gameState = new GameState();

			iniciarTabuleiro(gameState);

			GameController controller = new GameController(frame, gameState);

			SquareButton[][] squares = frame.getBoardPanel().getSquares();

			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					squares[row][col].addActionListener(controller);
				}
			}

			frame.getBoardPanel().renderBoard(gameState.getBoard());
			frame.setVisible(true);
		});
	}

	private static void iniciarTabuleiro(GameState game) {
		game.getBoard().setPiece(7, 0, new Piece(PieceType.TORRE, PieceColor.WHITE));
		game.getBoard().setPiece(7, 1, new Piece(PieceType.CAVALO, PieceColor.WHITE));
		game.getBoard().setPiece(7, 2, new Piece(PieceType.BISPO, PieceColor.WHITE));
		game.getBoard().setPiece(7, 3, new Piece(PieceType.RAINHA, PieceColor.WHITE));
		game.getBoard().setPiece(7, 4, new Piece(PieceType.REI, PieceColor.WHITE));
		game.getBoard().setPiece(7, 5, new Piece(PieceType.BISPO, PieceColor.WHITE));
		game.getBoard().setPiece(7, 6, new Piece(PieceType.CAVALO, PieceColor.WHITE));
		game.getBoard().setPiece(7, 7, new Piece(PieceType.TORRE, PieceColor.WHITE));

		for (int col = 0; col < 8; col++) {
			game.getBoard().setPiece(6, col, new Piece(PieceType.PEAO, PieceColor.WHITE));
		}

		game.getBoard().setPiece(0, 0, new Piece(PieceType.TORRE, PieceColor.BLACK));
		game.getBoard().setPiece(0, 1, new Piece(PieceType.CAVALO, PieceColor.BLACK));
		game.getBoard().setPiece(0, 2, new Piece(PieceType.BISPO, PieceColor.BLACK));
		game.getBoard().setPiece(0, 3, new Piece(PieceType.RAINHA, PieceColor.BLACK));
		game.getBoard().setPiece(0, 4, new Piece(PieceType.REI, PieceColor.BLACK));
		game.getBoard().setPiece(0, 5, new Piece(PieceType.BISPO, PieceColor.BLACK));
		game.getBoard().setPiece(0, 6, new Piece(PieceType.CAVALO, PieceColor.BLACK));
		game.getBoard().setPiece(0, 7, new Piece(PieceType.TORRE, PieceColor.BLACK));

		for (int col = 0; col < 8; col++) {
			game.getBoard().setPiece(1, col, new Piece(PieceType.PEAO, PieceColor.BLACK));
		}
	}
}