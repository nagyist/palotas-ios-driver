UIATarget.localTarget().setTimeout(0);

try {
  UIAutomation.register();
  UIAutomation.commandLoop();
} catch (err) {
  log("err" + JSON.stringify(err));
}
