package com.example.crocusoft_mova.presentation.dashboard.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.dashboard.profile.ProfileContract

@Composable
fun SettingsItem(item: SettingsItemModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = BaseTheme.dimens.dp5)
            .clickable(
                onClick = item.action
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp3)
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = null,
                modifier = Modifier.size(BaseTheme.dimens.dp7),
                tint = colorResource(Colors.white)
            )

            Text(
                text = stringResource(item.titleRes),
                style = BaseTheme.textStyle.t14SemiBold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp5)
        ){
            if(item.trailingText!=null){
                Text(text = item.trailingText, style = BaseTheme.textStyle.t16SemiBoldWhite)
            }
            if(item.isSwitch){
                Switch(
                checked = item.switchChecked,
                onCheckedChange = { isChecked ->
                    item.onCheckedChange?.invoke(isChecked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = colorResource(Colors.white),
                    checkedTrackColor = colorResource(Colors.secondary)
                )
            )
            } else{
                Icon(
                    painter = painterResource(Drawables.icon_right),
                    contentDescription = null,
                    modifier = Modifier.size(BaseTheme.dimens.dp5),
                    tint = colorResource(Colors.white)
                )
            }
        }


    }

}


data class SettingsItemModel(
    val icon: Int,
    val titleRes: Int,
    val trailingText : String?=null,
    val isSwitch : Boolean = false,
    val switchChecked : Boolean = false,
    val onCheckedChange: ((Boolean) -> Unit)? = null,
    val action: () -> Unit = {}
    ) {


}

