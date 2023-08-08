# picture-factory
Console utility for converting bitmaps into ASCII characters

![preview](./assets/preview.png)


### Usage
1. Download the compiled jar file (or create it yourself) and place it in any directory convenient for you.
2. Run the jar file by first creating a file with conversion settings (the diagram is shown below) and passing all the necessary arguments
```shell
java -jar picture-factory-1.0.1.jar <folder> <result>
```


### Settings scheme
Settings must be in the same directory as the jar file
```json lines
// settings.json
{
    "width": 512,
    "height": 512,
    "aspectRatio": 0.3125
}
```
