
# CommandLib

CommandLib is a lightweight command handling library for Bukkit/Spigot plugins. It simplifies the process of creating and managing commands and subcommands, allowing developers to focus on their plugin's functionality.

## Features

- Easy command and subcommand registration
- Automatic tab completion support
- Clear usage messages for commands
- Flexible structure for command handling



## Usage Example

Here’s a complete example of how to use CommandLib in your plugin:

```java
public class YourPlugin extends JavaPlugin {  
    @Override  
    public void onEnable() {  
        // Initialize CommandLib with this plugin instance  
        CommandLib.initialize(this);  
  
        // Register your commands  
        CommandLib.registerCommand(new MyCommand());  
    }  
}
```

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/Mardssss/CommandLib/blob/master/LICENSE) file for details.
