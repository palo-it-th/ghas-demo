# 🚨 Pull Request Vulnerability Detection Demo

## 🎯 What This PR Demonstrates

This PR intentionally introduces **4 types of vulnerabilities** to show how GitHub Advanced Security catches them BEFORE they reach production:

### 1. **Dependency Vulnerability** (Dependabot)
- Added `jackson-databind:2.9.8` with 12 known CVEs
- Severity: HIGH (CVSS 7.5)
- Attack: Remote Code Execution via deserialization

### 2. **SQL Injection** (Code Scanning - CodeQL)
- Vulnerable endpoint: `/api/user?username=`
- Attack: `username=admin' OR '1'='1`
- Impact: Full database access

### 3. **Cross-Site Scripting (XSS)** (Code Scanning - CodeQL)
- Vulnerable endpoint: `/api/greet?name=`
- Attack: `name=<script>alert('XSS')</script>`
- Impact: Session hijacking, credential theft

### 4. **Path Traversal** (Code Scanning - CodeQL)
- Vulnerable endpoint: `/api/file?path=`
- Attack: `path=../../etc/passwd`
- Impact: Arbitrary file read

---

## 🎬 Demo Script: Pull Request Security Review

### PART 1: Create the Pull Request (1 minute)

#### Step 1: Open Pull Request
```bash
# Already on branch: demo/add-vulnerable-dependency
git add build.gradle src/main/java/com/lab/ghas/VulnerableApiController.java
git commit -m "Add new API endpoints and JSON library"
git push -u origin demo/add-vulnerable-dependency
```

#### Step 2: Navigate to GitHub and Create PR
```
Go to: https://github.com/YOUR_USERNAME/security-ghas-demo/pulls
Click: "New pull request"
Base: main
Compare: demo/add-vulnerable-dependency
Title: "Add new API endpoints and Jackson library"
```

**Say:**
> "This looks like a normal feature PR—adding a JSON library and some API endpoints. Let's see what GitHub Advanced Security finds..."

---

### PART 2: Watch Security Checks Run (2-3 minutes)

#### Step 3: Show Security Checks in PR
In the PR, scroll to the bottom where checks run.

**What to point out:**
- ✅ **CodeQL Analysis** - Starting...
- ✅ **Dependency Review** - Starting...
- ⏱️ Wait 2-3 minutes for completion

**Say:**
> "GitHub is now scanning this PR for vulnerabilities. CodeQL analyzes the code for security issues, while Dependency Review checks for vulnerable libraries. This happens automatically on every PR."

---

### PART 3: Show Dependency Review Results (30 seconds)

#### Step 4: View Dependency Review Alert

Once checks complete, you'll see:
```
❌ Dependency Review
   1 vulnerable dependency detected
```

**Click on "Details"** to show:
- Package: `com.fasterxml.jackson.core:jackson-databind`
- Version: `2.9.8`
- Severity: **HIGH**
- Known CVEs: **12 vulnerabilities**
- Recommendation: Upgrade to `2.13.3+`

**Say:**
> "Dependency Review caught this BEFORE merge. Jackson 2.9.8 has 12 known vulnerabilities. If we merged this, our API would be exploitable. The PR is blocked until we fix this."

---

### PART 4: Show Code Scanning Results (2 minutes)

#### Step 5: View CodeQL Security Alerts

In the PR, you'll see:
```
❌ Code scanning results
   4 new security vulnerabilities found
```

**Click "View alerts"** and show each one:

##### Alert 1: SQL Injection
```
Location: VulnerableApiController.java:37
Query: "SELECT * FROM users WHERE username = '" + username + "'"
Severity: HIGH
Impact: Database breach via SQL injection
```

**Demonstrate:**
> "See line 37? User input goes directly into the SQL query. An attacker could use `' OR '1'='1` to dump the entire database. CodeQL detected this automatically."

##### Alert 2: Cross-Site Scripting (XSS)
```
Location: VulnerableApiController.java:59
Code: return "<html><body><h1>Hello, " + name + "!</h1></body></html>";
Severity: MEDIUM
Impact: Session hijacking, credential theft
```

**Demonstrate:**
> "Line 59 returns unsanitized user input in HTML. An attacker can inject JavaScript to steal cookies or credentials."

##### Alert 3: Path Traversal
```
Location: VulnerableApiController.java:72
Code: new File(path);
Severity: HIGH
Impact: Read arbitrary files (passwords, configs, source code)
```

**Demonstrate:**
> "Line 72 opens files based on user input. An attacker could read `/etc/passwd` or AWS credentials from the filesystem."

##### Alert 4: Hardcoded Credentials
```
Location: VulnerableApiController.java:22
Code: private static final String DB_PASSWORD = "SuperSecret123!";
Severity: MEDIUM
Impact: Database credential exposure
```

**Demonstrate:**
> "Line 22 has hardcoded database credentials in source code. These get committed to git history forever."

---

### PART 5: Block or Reject the PR (30 seconds)

#### Step 6: Show PR Protection

**Key talking point:**
> "With branch protection rules, we can REQUIRE these checks to pass before merging. This PR cannot be merged until the vulnerabilities are fixed."

**Show options:**
1. **Request changes** - "Fix the SQL injection and upgrade Jackson"
2. **Block merge** - PR merge button is disabled
3. **Auto-dismiss** - Close PR and revert changes

**Say:**
> "This is shift-left security in action. We caught 4 code vulnerabilities and 1 dependency vulnerability BEFORE they reached production. Manual code review would have missed most of these."

---

## 💰 Business Value Demonstrated

### Without GHAS in PR Review:
- ❌ SQL injection reaches production → Data breach
- ❌ Jackson vulnerability → Remote code execution
- ❌ XSS → User session hijacking
- ❌ Path traversal → Credential theft
- ❌ **Cost of breach: $4.45M average (IBM 2023)**

### With GHAS in PR Review:
- ✅ All vulnerabilities caught in PR
- ✅ Zero security issues reach production
- ✅ Automated - no manual security review needed
- ✅ **Cost: $0** (prevented before deployment)

---

## 🎯 Key Demo Talking Points

### On Speed:
> "From PR creation to security scan results: 3 minutes. Traditional AppSec review: 3 days. We're 1,000x faster."

### On Coverage:
> "CodeQL found 4 different vulnerability types in one scan. It knows 2,000+ security patterns across 10 languages."

### On Developer Experience:
> "Developers see security issues WHILE coding, not 3 weeks later in a security report. Problems are fixed when context is fresh."

### On Prevention:
> "Every vulnerability caught in PR is one less incident response. One prevented breach pays for GHAS for 100 years."

---

## 🎬 Advanced Demo Moves

### Live Fix Demo (Optional - Add 3 minutes)

If you want to show remediation:

#### Fix 1: Upgrade Jackson
```gradle
// Change from:
implementation 'com.fasterxml.jackson.core:jackson-databind:2.9.8'

// To:
implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.3'
```

#### Fix 2: Use Prepared Statements (SQL Injection)
```java
// Change from:
String query = "SELECT * FROM users WHERE username = '" + username + "'";

// To:
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
pstmt.setString(1, username);
```

#### Push Fixes:
```bash
git add build.gradle src/main/java/com/lab/ghas/VulnerableApiController.java
git commit -m "Fix security vulnerabilities from CodeQL scan"
git push origin demo/add-vulnerable-dependency
```

**Then show:**
- ✅ CodeQL re-runs automatically
- ✅ Alerts disappear
- ✅ PR now mergeable
- ✅ Green checkmarks

**Say:**
> "See how fast that was? Fix, push, checks re-run, alerts clear. From vulnerable to secure in under 5 minutes."

---

## 🔥 Handling Objections During Demo

### "Won't this slow down developers?"
**Response:**
> "This PR took 3 minutes to scan. Manual security review takes 3 days. We're SPEEDING UP development by catching issues early."

### "What about false positives?"
**Response:**
> "Look at these 4 alerts—every single one is a real, exploitable vulnerability. CodeQL has 99%+ accuracy. And developers can dismiss non-applicable findings with justification."

### "We already do code review"
**Response:**
> "Would a human reviewer catch the SQL injection on line 37? Maybe. Would they catch all 12 CVEs in Jackson 2.9.8? No chance. Humans review logic, security tools find vulnerabilities."

### "This looks complicated"
**Response:**
> "What's complicated? The developer pushed code, GitHub scanned it, alerts appeared in the PR. Zero configuration, zero maintenance, built into their existing workflow."

---

## 📊 Metrics to Highlight

### In This PR:
- Vulnerabilities found: **5**
- Time to detect: **3 minutes**
- Cost to fix: **$200** (1 hour dev time)
- Cost if deployed: **$4.5M+** (average breach cost)
- **ROI: 22,500%**

### Organization-Wide (Project):
- PRs scanned per week: 100+
- Vulnerabilities caught: 20-30/week
- Production incidents prevented: 4-6/month
- Annual value: **$1M+**

---

## 🎁 Demo Deliverables

**After showing this PR, provide:**
1. ✅ Link to this PR for them to explore
2. ✅ Fork of repo with vulnerabilities to test themselves
3. ✅ Documentation on setting up branch protection rules
4. ✅ CodeQL query catalog (2,000+ security patterns)
5. ✅ Case study: "How [Company] caught 500 vulnerabilities in PRs"

---

## 🚀 Call to Action

> "This PR demonstrates something crucial: Security doesn't have to slow down development. When it's automated, integrated, and developer-friendly, security becomes a competitive advantage.
>
> Every PR in your organization could have this level of security review. Every vulnerability could be caught before production. Every breach could be prevented.
>
> The question is: Can you afford NOT to have this?"

**Next steps:**
1. Enable GHAS on your repositories today
2. Set up branch protection to require security checks
3. Train developers on addressing security alerts
4. Watch your production incidents drop to zero

---

## 📞 Technical Setup (For Reference)

To replicate this demo:

1. **Enable Code Scanning:**
   - Go to Settings → Security & analysis
   - Enable "Code scanning"
   - Use CodeQL Analysis workflow

2. **Enable Dependency Review:**
   - Automatically enabled with Dependabot
   - Shows vulnerable dependencies in PRs

3. **Set Branch Protection:**
   - Settings → Branches → Add rule
   - Require "CodeQL" check to pass
   - Require "Dependency Review" to pass
   - Now PRs with vulnerabilities cannot merge

4. **Optional: Auto-fix Suggestions:**
   - CodeQL can suggest fixes
   - Copilot can auto-remediate some issues
   - Dependabot auto-creates update PRs
