package com.pluginjam.dungeon.generator;

public abstract class DungeonPiece {
    private String[] variants;

    public DungeonPiece(String ... variants) {
        this.variants = variants;
    }

    public String[] getVariants() {
        return variants;
    }

    public DungeonPieceSchematic toSchematic(int labelId) {
        return new DungeonPieceSchematic(variants[labelId]);
    }

}
