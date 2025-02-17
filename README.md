# GitHub Advanced Security (GHAS) Demo Project

This project demonstrates key **GitHub Advanced Security (GHAS)** features, including **Code Scanning, Secret Scanning, and Dependency Management**.

## 📌 Features

### 🔍 Code Scanning

**Objective:** Identify and fix security vulnerabilities in the source code using **CodeQL analysis**.

The **[`code-scan.yml`](.github/workflows/code-scan.yml)** GitHub workflow

- Runs all custom queries from the **[`custom-queries`](custom-queries)** folder
- The scan runs on push and pull requests to **`main`** branch.
- Supports multi-language analysis (Currently Java)

### 🔑 Secret Scanning

**Objective:** Detect and prevent accidental exposure of **secrets**.

GitHub Secret Scanning identifies exposed secrets in the **[`application.properties`](src/main/resources/application.properties)** file.

### 📦 Dependency Management

**Objective:** Continuously monitor dependencies for known vulnerabilities.

#### 🔔 Dependabot Alerts

- For **Gradle projects**, the **[`dependency-submission.yml`](.github/workflows/dependency-submission.yml)** GitHub workflow submits dependencies to the **Dependency Graph** via the **Dependency Submission API**.
- This enables **Dependabot Alerts** to detect vulnerabilities in dependencies.

#### 🔄 Dependabot Version Updates

- Dependabot scans dependency files for outdated versions.
- Automatically creates individual **pull requests (PRs)** for updates.
- Configuration is managed via **[`dependabot.yml`](.github/dependabot.yml)**.

## 🚀 Pre-requisites

1. **Enable GitHub Advanced Security (GHAS)** for this repository.
2. **Review and configure** the **[`dependabot.yml`](.github/dependabot.yml)** and **[`dependency-submission.yml`](.github/workflows/dependency-submission.yml)** files.
3. Enable the set up **Code Scanning**.
4. **Monitor security alerts** in the GitHub Security tab.

---

🔹 _Created for demonstration purposes._ 🚀
