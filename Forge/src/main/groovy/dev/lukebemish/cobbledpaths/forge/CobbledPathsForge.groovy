/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.forge

import com.matyrobbrt.gml.GMod
import dev.lukebemish.cobbledpaths.CobbledPathsCommon
import dev.lukebemish.cobbledpaths.Constants
import groovy.transform.CompileStatic
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.minecraftforge.event.CreativeModeTabEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@GMod(Constants.MOD_ID)
@CompileStatic
class CobbledPathsForge {
    CobbledPathsForge() {
        CobbledPathsCommon.init()
        modBus.addListener(FMLCommonSetupEvent) {
            it.enqueueWork {
                CobbledPathsCommon.setupTransforms()
            }
        }
        modBus.addListener(CreativeModeTabEvent.BuildContents) {
            onTabsSetup(it)
        }
    }

    static void onTabsSetup(CreativeModeTabEvent.BuildContents event) {
        if (event.tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(CobbledPathsCommon.COBBLE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.STONE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.DEEPSLATE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            event.accept(CobbledPathsCommon.ICE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.BLUE_ICE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            event.accept(CobbledPathsCommon.NETHER_BRICK_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.BLACKSTONE_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            event.accept(CobbledPathsCommon.OBSIDIAN_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.PURPUR_SETT.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)

            event.accept(CobbledPathsCommon.MOSS_BALL.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
        } else if (event.tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(CobbledPathsCommon.SPADE.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
            event.accept(CobbledPathsCommon.SLEDGEHAMMER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)
        }
    }
}
