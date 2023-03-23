package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), ImageDispalyFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //the same as val mainViewModel = ViewModelProvider(this).getImageIds()

        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        mainViewModel.setImageIds(imageArray)

        // Attach an instance of ImageDisplayFragment using factory method
        //val fragment = ImageDisplayFragment.newInstance(imageArray)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.imageFragmentContainerView, ImageDisplayFragment())
            .commit()
    }
    override fun itemSelected(itemId: Int){
        Toast.makeText(this, "You selected $itemId", Toast.LENGTH_SHORT).show()
    }
}