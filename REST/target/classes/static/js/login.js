function changeForm() {
  var loginPanel = document.getElementById("loginPanel");
  var registerPanel = document.getElementById("registerPanel");

  var loginForm = document.getElementById("loginForm");
  var registerForm = document.getElementById("registerForm");

  if (loginPanel.style.display === "none") {
    loginPanel.style.display = "block";
    registerPanel.style.display = "none";

    loginForm.style.display = "block";
    registerForm.style.display = "none";
  } else {
    loginPanel.style.display = "none";
    registerPanel.style.display = "block";

    loginForm.style.display = "none";
    registerForm.style.display = "block";
  }
}

