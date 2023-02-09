/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.quilt

import dev.lukebemish.cobbledpaths.CobbledPathsCommon
import groovy.transform.CompileStatic
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

@CompileStatic
class CobbledPathsQuilt implements ModInitializer {
    @Override
    void onInitialize(ModContainer mod) {
        CobbledPathsCommon.init()
        CobbledPathsCommon.setupTransforms()

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register({ FabricItemGroupEntries entries ->
            entries.accept(CobbledPathsCommon.COBBLE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.STONE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.DEEPSLATE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            entries.accept(CobbledPathsCommon.ICE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.BLUE_ICE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            entries.accept(CobbledPathsCommon.NETHER_BRICK_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.BLACKSTONE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            entries.accept(CobbledPathsCommon.OBSIDIAN_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.PURPUR_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            entries.accept(CobbledPathsCommon.MOSS_BALL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
        } as ItemGroupEvents.ModifyEntries)

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register({ FabricItemGroupEntries entries ->
            entries.accept(CobbledPathsCommon.SPADE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            entries.accept(CobbledPathsCommon.SLEDGEHAMMER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
        } as ItemGroupEvents.ModifyEntries)
    }
}
