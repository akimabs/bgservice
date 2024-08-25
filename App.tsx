import React from 'react';
import {Button, StyleSheet, View, NativeModules} from 'react-native';

const {CustomModule} = NativeModules;

const checkBridgeFunction = () => {
  CustomModule.checkBridge();
};

const startSessionFunction = () => {
  CustomModule.startService();
};

const stopSessionFunction = () => {
  CustomModule.stopService();
};

const checkSessionFunction = () => {
  CustomModule.isBackgroundServiceRunning();
};

function App() {
  return (
    <View style={styles.container}>
      <Button title="Click It" onPress={() => checkBridgeFunction()} />
      <Button
        title="Start Session Background"
        onPress={() => startSessionFunction()}
      />
      <Button
        title="Stop Session Background"
        onPress={() => stopSessionFunction()}
      />
      <Button
        title="Check Session Background"
        onPress={() => checkSessionFunction()}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {flex: 1, justifyContent: 'center', alignItems: 'center'},
});

export default App;
