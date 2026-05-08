package com.example.crocusoft_mova.common.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables


@Composable
fun ProfileAvatar(
    onClick: (Uri) -> Unit = {},
    avatar: Uri?,
) {


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            onClick(uri ?: Uri.EMPTY)
        }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Box(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {

            if (avatar == null) {
                Image(
                    painter = painterResource(Drawables.profile_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(BaseTheme.dimens.avatar_size)
                )

            } else {
                Box(
                    modifier = Modifier.align(alignment = Alignment.Center)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(BaseTheme.dimens.avatar_size)
                            .clip(CircleShape),
                        model = avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }
            }



            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
            ) {
                Icon(
                    painter = painterResource(Drawables.icon_edit),
                    contentDescription = null,
                    tint = colorResource(
                        Colors.secondary
                    )
                )
            }

        }


    }


}