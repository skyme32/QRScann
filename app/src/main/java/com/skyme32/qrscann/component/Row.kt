package com.skyme32.qrscann.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skyme32.qrscann.R


@Composable
fun RowMainScreen(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        elevation = dimensionResource(id = R.dimen.padding_small),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RowImage(resource = R.drawable.ic_launcher_foreground, description = "MyImage")
            Spacer(modifier = modifier.padding(1.dp))
            RowText(text = "Title Hello Compose")
        }
    }
}

@Preview
@Composable
fun DefaultRowScreen() {
    RowMainScreen()
}