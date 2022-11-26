package com.pluginjam.dungeon.generator;

public class SingleVariantDungeonPiece extends DungeonPiece {

    public SingleVariantDungeonPiece(String label) {
        super(label);
    }

    public String getLabel() {
        return getVariants()[0];
    }

    public DungeonPieceSchematic toSchematic() {
        return new DungeonPieceSchematic(getLabel());
    }
}
