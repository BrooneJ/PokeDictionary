/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.core.presentation.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

/**
 * Color palette for the app.
 * Copy from Now in Android.
 */
internal val Blue10 = Color(0xFF001F28)
internal val Blue20 = Color(0xFF003544)
internal val Blue30 = Color(0xFF004D61)
internal val Blue40 = Color(0xFF006780)
internal val Blue80 = Color(0xFF5DD5FC)
internal val Blue90 = Color(0xFFB8EAFF)
internal val DarkGreen10 = Color(0xFF0D1F12)
internal val DarkGreen20 = Color(0xFF223526)
internal val DarkGreen30 = Color(0xFF394B3C)
internal val DarkGreen40 = Color(0xFF4F6352)
internal val DarkGreen80 = Color(0xFFB7CCB8)
internal val DarkGreen90 = Color(0xFFD3E8D3)
internal val DarkGreenGray10 = Color(0xFF1A1C1A)
internal val DarkGreenGray20 = Color(0xFF2F312E)
internal val DarkGreenGray90 = Color(0xFFE2E3DE)
internal val DarkGreenGray95 = Color(0xFFF0F1EC)
internal val DarkGreenGray99 = Color(0xFFFBFDF7)
internal val DarkPurpleGray10 = Color(0xFF201A1B)
internal val DarkPurpleGray20 = Color(0xFF362F30)
internal val DarkPurpleGray90 = Color(0xFFECDFE0)
internal val DarkPurpleGray95 = Color(0xFFFAEEEF)
internal val DarkPurpleGray99 = Color(0xFFFCFCFC)
internal val Green10 = Color(0xFF00210B)
internal val Green20 = Color(0xFF003919)
internal val Green30 = Color(0xFF005227)
internal val Green40 = Color(0xFF006D36)
internal val Green80 = Color(0xFF0EE37C)
internal val Green90 = Color(0xFF5AFF9D)
internal val GreenGray30 = Color(0xFF414941)
internal val GreenGray50 = Color(0xFF727971)
internal val GreenGray60 = Color(0xFF8B938A)
internal val GreenGray80 = Color(0xFFC1C9BF)
internal val GreenGray90 = Color(0xFFDDE5DB)
internal val Orange10 = Color(0xFF380D00)
internal val Orange20 = Color(0xFF5B1A00)
internal val Orange30 = Color(0xFF812800)
internal val Orange40 = Color(0xFFA23F16)
internal val Orange80 = Color(0xFFFFB59B)
internal val Orange90 = Color(0xFFFFDBCF)
internal val Purple10 = Color(0xFF36003C)
internal val Purple20 = Color(0xFF560A5D)
internal val Purple30 = Color(0xFF702776)
internal val Purple40 = Color(0xFF8B418F)
internal val Purple80 = Color(0xFFFFA9FE)
internal val Purple90 = Color(0xFFFFD6FA)
internal val PurpleGray30 = Color(0xFF4D444C)
internal val PurpleGray50 = Color(0xFF7F747C)
internal val PurpleGray60 = Color(0xFF998D96)
internal val PurpleGray80 = Color(0xFFD0C3CC)
internal val PurpleGray90 = Color(0xFFEDDEE8)
internal val Red10 = Color(0xFF410002)
internal val Red20 = Color(0xFF690005)
internal val Red30 = Color(0xFF93000A)
internal val Red40 = Color(0xFFBA1A1A)
internal val Red80 = Color(0xFFFFB4AB)
internal val Red90 = Color(0xFFFFDAD6)
internal val Teal10 = Color(0xFF001F26)
internal val Teal20 = Color(0xFF02363F)
internal val Teal30 = Color(0xFF214D56)
internal val Teal40 = Color(0xFF3A656F)
internal val Teal80 = Color(0xFFA2CED9)
internal val Teal90 = Color(0xFFBEEAF6)

/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Immutable
data class PokedexColors(
  val primary: Color,
  val background: Color,
  val backgroundLight: Color,
  val backgroundDark: Color,
  val absoluteWhite: Color,
  val absoluteBlack: Color,
  val white: Color,
  val white12: Color,
  val white56: Color,
  val white70: Color,
  val black: Color,
  val gray21: Color,
  val bug: Color,
  val dark: Color,
  val dragon: Color,
  val electric: Color,
  val fairy: Color,
  val fire: Color,
  val fighting: Color,
  val flying: Color,
  val ghost: Color,
  val steel: Color,
  val ice: Color,
  val poison: Color,
  val psychic: Color,
  val rock: Color,
  val water: Color,
  val grass: Color,
  val ground: Color,
  val orange: Color,
  val green: Color,
  val blue: Color,
) {

  companion object {
    /**
     * Provides the default colors for the light mode of the app.
     *
     * @return A [PokedexColors] instance holding our color palette.
     */
    @Composable
    fun defaultDarkColors(): PokedexColors = PokedexColors(
      primary = colorResource(id = R.color.colorPrimary),
      background = colorResource(id = R.color.background_dark),
      backgroundLight = colorResource(id = R.color.background800_dark),
      backgroundDark = colorResource(id = R.color.background900_dark),
      absoluteWhite = colorResource(id = R.color.white),
      absoluteBlack = colorResource(id = R.color.black),
      white = colorResource(id = R.color.white_dark),
      white12 = colorResource(id = R.color.white_12_dark),
      white56 = colorResource(id = R.color.white_56_dark),
      white70 = colorResource(id = R.color.white_70_dark),
      black = colorResource(id = R.color.black_dark),
      gray21 = colorResource(id = R.color.gray_21),
      bug = colorResource(id = R.color.bug),
      dark = colorResource(id = R.color.dark),
      dragon = colorResource(id = R.color.dragon),
      electric = colorResource(id = R.color.electric),
      fairy = colorResource(id = R.color.fairy),
      fire = colorResource(id = R.color.fire),
      fighting = colorResource(id = R.color.fighting),
      flying = colorResource(id = R.color.flying),
      ghost = colorResource(id = R.color.ghost),
      steel = colorResource(id = R.color.steel),
      ice = colorResource(id = R.color.ice),
      poison = colorResource(id = R.color.poison),
      psychic = colorResource(id = R.color.psychic),
      rock = colorResource(id = R.color.rock),
      water = colorResource(id = R.color.water),
      grass = colorResource(id = R.color.grass),
      ground = colorResource(id = R.color.ground),
      orange = colorResource(id = R.color.orange),
      green = colorResource(id = R.color.green),
      blue = colorResource(id = R.color.blue),
    )

    /**
     * Provides the default colors for the light mode of the app.
     *
     * @return A [PokedexColors] instance holding our color palette.
     */
    @Composable
    fun defaultLightColors(): PokedexColors = PokedexColors(
      primary = colorResource(id = R.color.colorPrimary),
      background = colorResource(id = R.color.background),
      backgroundLight = colorResource(id = R.color.background800),
      backgroundDark = colorResource(id = R.color.background900),
      absoluteWhite = colorResource(id = R.color.white),
      absoluteBlack = colorResource(id = R.color.black),
      white = colorResource(id = R.color.white),
      white12 = colorResource(id = R.color.white_12),
      white56 = colorResource(id = R.color.white_56),
      white70 = colorResource(id = R.color.white_70),
      black = colorResource(id = R.color.black),
      gray21 = colorResource(id = R.color.gray_21),
      bug = colorResource(id = R.color.bug),
      dark = colorResource(id = R.color.dark),
      dragon = colorResource(id = R.color.dragon),
      electric = colorResource(id = R.color.electric),
      fairy = colorResource(id = R.color.fairy),
      fire = colorResource(id = R.color.fire),
      fighting = colorResource(id = R.color.fighting),
      flying = colorResource(id = R.color.flying),
      ghost = colorResource(id = R.color.ghost),
      steel = colorResource(id = R.color.steel),
      ice = colorResource(id = R.color.ice),
      poison = colorResource(id = R.color.poison),
      psychic = colorResource(id = R.color.psychic),
      rock = colorResource(id = R.color.rock),
      water = colorResource(id = R.color.water),
      grass = colorResource(id = R.color.grass),
      ground = colorResource(id = R.color.ground),
      orange = colorResource(id = R.color.orange),
      green = colorResource(id = R.color.green),
      blue = colorResource(id = R.color.blue),
    )
  }
}