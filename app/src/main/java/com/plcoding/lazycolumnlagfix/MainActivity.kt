@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)

package com.plcoding.lazycolumnlagfix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.lazycolumnlagfix.ui.theme.LazyColumnLagFixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val compressedResourceIds = listOf(
            R.drawable.pic1_compressed,
            R.drawable.pic2_compressed,
            R.drawable.pic3_compressed,
            R.drawable.pic4_compressed,
        )
        val tags = listOf(
            "speaker",
            "android",
            "audience",
        )
        val images = (1..100).map {
            MyImage(
                resId = compressedResourceIds.random(),
                title = "Random title $it",
                tags = tags
                    .shuffled()
                    .take((1..3).random())
            )
        }
        setContent {
            LazyColumnLagFixTheme {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = images,
                        key = {
                            it.id
                        }
                    ) { image ->
                        ImageDetails(image)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageDetails(
    image: MyImage,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(image.resId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = image.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                image.tags.forEach { tag ->
                    SuggestionChip(
                        label = {
                            Text(text = tag)
                        },
                        onClick = {},
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}
