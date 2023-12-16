package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoStatus;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoType;

public interface SweetPotatoProperties {
    SweetPotatoStatus getStatus();
    SweetPotatoType asType();
}
