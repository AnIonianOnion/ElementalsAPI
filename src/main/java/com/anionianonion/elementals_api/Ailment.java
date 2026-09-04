package com.anionianonion.elementals_api;

public record Ailment(String name, AilmentType ailmentType) {

    public enum AilmentType {DAMAGING, NON_DAMAGING}

    public void onTick(float inputDamage) {

    }
}
