package TigersDen.BL.AI.BussinesLogic;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.AI.Contract.ITigerDenBrain;
import TigersDen.BL.AI.DataModel.Move;
import TigersDen.BL.BoardService.BussinessLogic.Coordinate;
import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.BoardValidator.Contract.IBoardValidator;

public class TigerDenBrain implements ITigerDenBrain {
    private final int MAX_DEPTH = 3;
    private IBoardValidator boardValidator;

    @Inject
    public TigerDenBrain(IBoardValidator boardValidator, IBoard b) {
        this.boardValidator = boardValidator;
    }

    @Override
    public Move minimax(IBoard boardToMakeMoveOnit, int depth, boolean isTigerPlayer) throws Exception {
        try {
            if (boardValidator.getWinnerRole() != null || depth == MAX_DEPTH) {
                return new Move(-1, -1, -1, -1, evaluate(boardToMakeMoveOnit));
            }

            if (isTigerPlayer) {
                List<Move> possibleMoves = generatePossibleMovesForTiger(boardToMakeMoveOnit);
                if (possibleMoves.size() == 0) {
                    throw new Exception("No possible moves for the tiger player.");
                }

                Move bestMove = possibleMoves.get(0);

                for (Move move : possibleMoves) {
                    // Make a copy of the game state and apply the move
                    IBoard newGameState = boardToMakeMoveOnit.clone();
                    applyMove(newGameState, move);

                    // Recursively call the minimax method for the opponent player
                    Move result = minimax(newGameState, depth + 1, false);
                    move.setEvaluationAfterTheMove(result.getEvaluationAfterTheMove());

                    // Update the best move if a higher score is found
                    if (result.getEvaluationAfterTheMove() > bestMove.getEvaluationAfterTheMove()) {
                        bestMove = move;
                    }
                }
                return bestMove;
            } else {
                List<Move> possibleMoves = generatePossibleMovesForPawns(boardToMakeMoveOnit);
                if (possibleMoves.size() == 0) {
                    throw new Exception("No possible moves for the pawns player.");
                }
                Move bestMove = possibleMoves.get(0);

                for (Move move : possibleMoves) {
                    // Make a copy of the game state and apply the move
                    IBoard newGameState = boardToMakeMoveOnit.clone();
                    applyMove(newGameState, move);

                    // Recursively call the minimax method for the opponent player
                    Move result = minimax(newGameState, depth + 1, true);
                    move.setEvaluationAfterTheMove(result.getEvaluationAfterTheMove());

                    // Update the best move if a lower score is found
                    if (result.getEvaluationAfterTheMove() < bestMove.getEvaluationAfterTheMove()) {
                        bestMove = move;
                    }
                }
                return bestMove;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void applyMove(IBoard board, Move move) {
        if (move == null) {
            throw new NullPointerException("Move is null.");
        }
        ICoordinate sourceCoordinate = null;
        if (move.getSourceColumn() == 4 && move.getSourceRow() == -1) {
            sourceCoordinate = Coordinate.createSpacialInstance();
        } else {
            sourceCoordinate = Coordinate.createInstance(move.getSourceRow(), move.getSourceColumn(), false);
        }
        if (sourceCoordinate == null) {
            throw new NullPointerException("Source coordinate is null.");
        }
        ICell sourceCell = board.getCell(sourceCoordinate);
        if (sourceCell == null) {
            throw new NullPointerException("Source cell is null.");
        }
        IPiece movingPiece = sourceCell.getPieceOnIt();
        if (movingPiece == null) {
            throw new NullPointerException("Moving piece is null.");
        }
        ICoordinate targetCoordinate = null;
        if (move.getTargetColumn() == 4 && move.getTargetRow() == -1) {
            targetCoordinate = Coordinate.createSpacialInstance();
        } else {
            targetCoordinate = Coordinate.createInstance(move.getTargetRow(), move.getTargetColumn(), false);
        }
        if (targetCoordinate == null) {
            throw new NullPointerException("Target coordinate is null.");
        }
        ICell targetCell = board.getCell(targetCoordinate);
        if (targetCell == null) {
            throw new NullPointerException("Target cell is null.");
        }
        sourceCell.setPieceOnIt(null);
        targetCell.setPieceOnIt(movingPiece);
        movingPiece.setCoordinate(targetCell.getCoordinate());
    }

    private List<Move> generatePossibleMovesForTiger(IBoard boardToMakeMoveOnIt) throws Exception {
        IPiece tigerPiece = null;
        for (IPiece piece : boardToMakeMoveOnIt.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("tiger")) {
                if (tigerPiece != null) {
                    throw new Exception("More than one tiger piece found on the board.");
                }
                tigerPiece = piece;
            }
        }
        if (tigerPiece == null) {
            throw new Exception("No tiger piece found on the board.");
        }

        List<Move> possibleMoves = new ArrayList<>();
        for (ICell optionalCell : tigerPiece.getOptionalMovements(boardToMakeMoveOnIt)) {
            ICoordinate sourceCor = tigerPiece.getCoordinate();
            ICoordinate targetCor = optionalCell.getCoordinate();
            Move move = new Move(sourceCor.getColumn(),
                    sourceCor.getRow(),
                    targetCor.getColumn(),
                    targetCor.getRow(),
                    0);

            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private List<Move> generatePossibleMovesForPawns(IBoard boardToMakeMoveOint) throws Exception {
        List<Move> possibleMoves = new ArrayList<>();
        List<IPiece> optionalPieces = new ArrayList<>();

        for (IPiece piece : boardToMakeMoveOint.getPieces()) {
            if (piece.getOwningPlayer().getRole().equals("pawns")) {
                optionalPieces.add(piece);
            }
        }

        for (IPiece optionalPiece : optionalPieces) {

            List<ICell> optionalMovements = optionalPiece.getOptionalMovements(boardToMakeMoveOint);
            for (ICell optionalCell : optionalMovements) {
                Move movingDetails = new Move(optionalPiece.getCoordinate().getColumn(),
                        optionalPiece.getCoordinate().getRow(),
                        optionalCell.getCoordinate().getColumn(),
                        optionalCell.getCoordinate().getRow(),
                        0);
                possibleMoves.add(movingDetails);
            }
        }
        return possibleMoves;
    }

    private double evaluate(IBoard board) {
        int numPiecesNotCaptured = countPiecesCaptured(board);
        double totalDistanceToTiger = calculateTotalDistancesSquaredToTiger(board);

        double piecesWeight = 1;
        double distanceWeight = 100;

        return piecesWeight * numPiecesNotCaptured + distanceWeight * totalDistanceToTiger;
    }

    private int countPiecesCaptured(IBoard board) {
        int count = 0;

        for (IPiece piece : board.getPieces()) {
            if (piece.isCaptured()) {
                count++;
            }
        }
        return count;
    }

    private double calculateTotalDistancesSquaredToTiger(IBoard board) {
        int totalDistance = 0;
        IPiece tigerPiece = board.getPieces().stream()
                                 .filter(p -> p.getOwningPlayer()
                                               .getRole().equals("tiger"))
                                .findFirst()
                                .get();

        for (IPiece piece : board.getPieces()) {
            if (!piece.isCaptured() && piece.getOwningPlayer().getRole().equals("pawns")) {
                double distance = piece.getCoordinate().getDistanceTo(tigerPiece.getCoordinate());
                totalDistance += Math.pow(distance, 2);
            }
        }
        return totalDistance;
    }

   
}
