
With the preview version of [android L](http://developer.android.com/preview/reference.html), Google has introduced [two new widgets](https://developer.android.com/preview/material/ui-widgets.html):

- Cardview
- RecyclerView 

## Usage

The RecyclerView comes into play when the purpose is to show many Views repeatedly, lists, grids, etc ..., when the views don't fit into the screen. 

RecyclerView implements a system to accomplish this task easily and efficiently. 

## Example 

![](http://androcode.es/wp-content/uploads/2014/10/rv_demo.gif)

## The RecyclerView API

Unlike the *ListView*, *GridView*, etc ... the *RecyclerView* is dedicated solely to what its name suggests, recycle, reuse resources and avoid the repeated use of the costly ```findViewById()```, do not worry about the visual appearance, that purpose if for the LayoutManager, One class, one task, that is the philosophy that follows the **RecyclerView API**, a bundle of inner classes, each with a responsibility: 

- Adapter 
- ViewHolder 
- LayoutManager 
- ItemDecoration 
- ItemAnimator 
- Adapter 

**RecyclerView.Adapter\<VH>**

This class is responsible for creating _Views_ needed for each element in a row or cell, _RecyclerView_ also is very close to _ViewHolder_, having to be indicated in the declaration of the class.

Many could think that this is not new, that Google and advise that [pattern](http://developer.android.com/training/improving-layouts/smooth-scrolling.html) time ago , this time Google force us the implementation in the Adapter, step forward, no doubt. 

The method ```OnCreateViewHolder(parentViewGroup ViewGroup, int i)``` initializes the _ViewHolder_ on the adapter:

```java
     Override 
     public ViewHolder onCreateViewHolder (parentViewGroup ViewGroup, int i) {

         View rowView = LayoutInflater.from (parentViewGroup.getContext ()) 
             .inflate (R.layout.list_basic_item, parentViewGroup, false); 

         return new ViewHolder (rowView); 
     } 
```

<br>
The ```onBindViewHolder(ViewHolder viewholder, int position)```method is used to set the content of the Views 

```java
     Override 
     public void onBindViewHolder (ViewHolder viewHolder, int position) {

         SampleModel RowData = sampleData.get end (position); 
         viewHolder.textViewSample.setText (rowData.getSampleText ()); 
         viewHolder.itemView.setTag (RowData); 
     } 
```

## RecyclerView.ViewHolder 

As I was saying, ViewHolder pattern is nothing new, Google actually recommend using it in a while, you can think the _ViewHolder_ as a cache of views can reuse rather than create them again. 


```java
    Override 
    public static class extends ViewHolder RecyclerView.ViewHolder {

         private final TextView textViewSample; 

         public ViewHolder (View itemView) {
             super (itemView); 

             textViewSample = (TextView) itemView.findViewById (
                 R.id.textViewSample); 
         } 
     } 
```


##RecyclerView.LayoutManager 

The _LayoutManager_ is responsible to layout all views in the _RecyclerView_, specifying the _LinearLayoutManager_ allows access among other items displayed on the screen as the first element, last, or, for example, the last fully visible, this horizontally or vertical, in the example used in a vertical arrangement. 
    
```java
         LinearLayoutManager LinearLayoutManager mLayoutManager = new (this); 
         recyclerView.setLayoutManager (mLayoutManager); 
```

##RecyclerView.ItemDecorator 

Another important class is the _ItemDecorator_, this class can alter the views of the RecycleView, this also provides also provides an element called insets (margins) that can be applied to views without modifying the layout parameters. 

In the example shown as _ItemDecorators_ used to draw a small divider between the elements of RecyclerView: 
    
```java

saulmm.com.recyclerviewproject package; 

android.content.Context import; 
android.content.res.TypedArray import; 
android.graphics.Canvas import; 
android.graphics.drawable.Drawable import; 
android.support.v7.widget.RecyclerView import; 
android.view.View import; 

public class extends SampleDivider RecyclerView.ItemDecoration {

     private static final int [] = {ATTRS android.R.attr.listDivider}; 

     private Drawable mDivider; 

     public SampleDivider (Context context) {
         TypedArray a = context.obtainStyledAttributes (ATTRS); 
         a.getDrawable mDivider = (0); 
         a.recycle (); 
     } 

     Override 
     public void onDrawOver (Canvas c, RecyclerView parent) {

         parent.getPaddingLeft int left = (); 
         int right = parent.getWidth () - parent.getPaddingRight (); 

         parent.getChildCount childCount = int (); 

         for (int i = 0; i <childCount; ++ i) {

             Child = parent.getChildAt view (i); 

             RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child 
                     .getLayoutParams (); 

             child.getBottom int top = () + params.bottomMargin; 
             int bottom = top + mDivider.getIntrinsicHeight (); 

             mDivider.setBounds (left, top, right, bottom); 
             mDivider.draw (c); 
         } 
     } 
} 
```

## ItemAnimator 

The _ItemAnimator_ class as the name suggests, animates the _RecyclerView_ when added or remove and an element, _RecyclerView_ implements a _DefaultItemAnimator_ by default.

The _RecyclerView_ must know when an item is inserted or deleted, with elements like ListViews, GridViews this was accomplished by calling ```notifyDataSetChanged ()``` method, in terms of performance, it is quite costly as it redraws all items in the layout, the same with the RecyclerView is using ```notifyItemInserted()``` method to add and ```notifyItemRemoved ()``` to remove, updating only the appropriate part. 

##References: 
[Wolfram RITTMEYER - A Glance at Android's first RecyclerView](http://www.grokkingandroid.com/first-glance-androids-recyclerview/)
[Wires are obsolete - Building a RecyclerView](http://wiresareobsolete.com/2014/09/recyclerview-layoutmanager-2/)
[Android L preview Reference](http://developer.android.com/preview/reference.html)
