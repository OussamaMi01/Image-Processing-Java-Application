# Image Processing Java Application

## Overview

🎨 **Curious about what you can create with Java and GitHub?** Check out my latest personal project, now live on GitHub! 🌟 This project demonstrates fundamental image processing techniques using Java, with the development done using IntelliJ IDEA.

This is **part 2** of my previous Java app about image descriptors. Dive into the world of image processing with this **user-friendly Java application** that allows you to perform various image manipulation tasks.

## Features

- **Image Selection**: Choose an image file from your device using a file chooser dialog.
- **Grayscale Conversion**: Convert the selected image to grayscale based on luminance values.
- **Threshold Filter**: Apply a binary threshold filter to create a black-and-white effect.
- **Image Resizing**: Resize the image by specifying new dimensions.
- **Edge Detection**: Use the Sobel operator for edge detection.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/OussamaMi01/image-processing-java.git
   ```

2. **Open the Project**:
   - Open IntelliJ IDEA.
   - Select `Open` and choose the cloned repository directory.

3. **Build the Project**:
   - Ensure you have the Java Development Kit (JDK) installed.
   - Build the project using IntelliJ IDEA’s build tools.

## Usage

1. **Run the Application**:
   - In IntelliJ IDEA, run the `ImageProcessingMain` class.

2. **Select an Image**:
   - A file chooser dialog will appear. Select the desired image file from your device.

3. **Process the Image**:
   - The application will automatically convert the image to grayscale, apply a threshold filter, resize it based on user input, and detect edges.

4. **Save Processed Images**:
   - The application will save the processed images at various stages with distinct filenames.

## Code Explanation

- **Main Class**:
  - The `main` method handles the overall workflow: image selection, processing, and saving.
- **Image Selection**:
  - `chooseImageFile()` method uses `JFileChooser` for file selection.
- **Image Processing**:
  - Grayscale conversion averages RGB values.
  - A threshold filter creates a binary image.
  - User inputs new dimensions for resizing.
  - Sobel operator applies edge detection.
- **Helper Functions**:
  - `applyFilter()` method applies the Sobel filter to each pixel.

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your changes.
3. Commit your changes and push them to your fork.
4. Create a pull request describing your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or feedback, please reach out to me via [GitHub](https://github.com/OussamaMi01).
