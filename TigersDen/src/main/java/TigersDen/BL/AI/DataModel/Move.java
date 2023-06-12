package TigersDen.BL.AI.DataModel;

import TigersDen.BL.BoardService.Contract.ICoordinate;

public class Move {
    private int sourceColumn;
    private int sourceRow;
    private int targetColumn;
    private int targetRow;
    private ICoordinate capturePieceCoordinate;
    private double evaluationAfterTheMove;

    public Move(int sourceColumn, int sourceRow, int targetColumn, int targetRow, ICoordinate capturePieceCoordinate,
            double evaluationAfterTheMove) {
        this.sourceColumn = sourceColumn;
        this.sourceRow = sourceRow;
        this.targetColumn = targetColumn;
        this.targetRow = targetRow;
        this.capturePieceCoordinate = capturePieceCoordinate;
        this.evaluationAfterTheMove = evaluationAfterTheMove;
    }

    public ICoordinate getCapturePieceCoordinate() {
        return capturePieceCoordinate;
    }

    public void setCapturePieceCoordinate(ICoordinate capturePieceCoordinate) {
         this.capturePieceCoordinate = capturePieceCoordinate;
    }

    public int getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(int sourceColumn) {
        this.sourceColumn = sourceColumn;
    }

    public int getSourceRow() {
        return sourceRow;
    }

    public void setSourceRow(int sourceRow) {
        this.sourceRow = sourceRow;
    }

    public int getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(int targetColumn) {
        this.targetColumn = targetColumn;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    public double getEvaluationAfterTheMove() {
        return evaluationAfterTheMove;
    }

    public void setEvaluationAfterTheMove(double evaluationAfterTheMove) {
        this.evaluationAfterTheMove = evaluationAfterTheMove;
    }

    @Override
    public String toString() {
        return "Move [sourceColumn=" + sourceColumn + ", sourceRow=" + sourceRow + ", targetColumn=" + targetColumn
                + ", targetRow=" + targetRow + ", evaluationAfterTheMove=" + evaluationAfterTheMove + "]";
    }
}
