# Mood-Based Playlist Generator Java

The Mood-Based Playlist Generator is a Java program that allows users to generate playlists of songs based on their current mood. Users can input their mood, and the program will display a selection of songs that match that mood or have synonyms associated with it.

## Features

- **Mood-Based Playlist Generation**: Users can input their mood, and the program will display songs that match that mood or have synonyms associated with it.
- **Customizable**: Users can specify the number of songs they want to include in the playlist.
- **CSV Data Input**: The program loads music data from a CSV file containing song titles, artists, moods, and synonyms.

## Usage

1. **Compile the Code**: Compile the Java source code using a Java compiler.
2. **Run the Program**: Run the compiled Java class `MusicDataSource`.
3. **Follow the Prompts**: The program will prompt you to input your current mood and the number of songs you want to include in the playlist.
4. **Enjoy the Music**: The program will display a selection of songs matching your mood or its synonyms.

## Data Format

The program expects music data to be in CSV format with the following columns:
- **Title**: Title of the song
- **Artist**: Artist name
- **Mood**: Mood associated with the song
- **Synonyms (optional)**: Additional synonyms for the mood (up to 6 columns)

## Dependencies

- Java Development Kit (JDK)
- CSV file containing music data

## Contributing

Contributions to the Mood-Based Playlist Generator project are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
