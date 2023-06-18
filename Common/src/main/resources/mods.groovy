/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

ModsDotGroovy.make {
    modLoader = 'gml'
    loaderVersion = '[1,)'

    license = 'LGPL-3.0-or-later'
    issueTrackerUrl = 'https://github.com/lukebemish/CobbledPaths/issues'

    mod {
        modId = this.buildProperties['mod_id']
        displayName = this.buildProperties['mod_name']
        version = this.version
        group = this.group
        onQuilt {
            intermediate_mappings = 'net.fabricmc:intermediary'
        }
        displayUrl = 'https://github.com/lukebemish/CobbledPaths'

        description = "Adds various cobbled and paved paths"
        authors = [this.buildProperties['mod_author'] as String]

        dependencies {
            minecraft = this.minecraftVersionRange

            forge {
                versionRange = ">=${this.forgeVersion}"
            }
            onForge {
                mod('gml') {
                    versionRange = ">=${this.libs.versions.gml}"
                }
            }

            onQuilt {
                mod('groovyduvet') {
                    versionRange = ">=${this.libs.versions.groovyduvet.all}"
                }
            }

            quiltLoader {
                versionRange = ">=${this.quiltLoaderVersion}"
            }
        }

        entrypoints {
            init = [
                    adapted {
                        adapter = 'groovyduvet'
                        value = 'dev.lukebemish.cobbledpaths.quilt.CobbledPathsQuilt'
                    }
            ]
        }
    }
    onQuilt {
        mixin = ['mixin.cobbledpaths_common.json']
    }
}
