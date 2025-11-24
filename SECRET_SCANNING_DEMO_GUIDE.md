# 🔐 Secret Scanning Demo Guide

## 🚨 Why Secret Scanning Might Not Show Alerts

### Common Reasons:

1. **Secret Scanning Not Enabled**
   - Go to: `Settings → Security & analysis`
   - Enable: "Secret scanning"
   - Enable: "Push protection" (recommended)

2. **Public vs Private Repos**
   - **Public repos**: Secret scanning is FREE and automatic
   - **Private repos**: Requires GitHub Advanced Security license

3. **Push Protection Blocks Before Alerts**
   - If push protection is enabled, secrets are BLOCKED at push time
   - They never reach GitHub, so no alerts are created
   - This is actually BETTER security!

4. **Pattern Matching Requirements**
   - GitHub uses specific patterns for known secret types
   - Custom secrets need to match GitHub's patterns
   - Generic passwords may not trigger alerts

---

## 🎯 This PR Contains 20 Types of Secrets

### Cloud Provider Credentials (Most Dangerous)
1. ✅ **AWS Access Key & Secret** - AKIAIOSFODNN7EXAMPLE
2. ✅ **Azure Storage Key** - Full account access
3. ✅ **Google Cloud API Key** - AIzaSy...

### Version Control & CI/CD
4. ✅ **GitHub Personal Access Token (Classic)** - ghp_...
5. ✅ **GitHub PAT (Fine-grained)** - github_pat_...
6. ✅ **NPM Token** - npm_...
7. ✅ **Docker Hub Token** - dckr_pat_...

### Payment & Financial Services
8. ✅ **Stripe Secret Key** - sk_live_... (Live key!)
9. ✅ **PayPal credentials** (if added)

### Communication Services
10. ✅ **Twilio Account SID & Auth Token**
11. ✅ **SendGrid API Key** - SG....
12. ✅ **Slack Webhook URL**
13. ✅ **Slack Bot Token** - xoxb-...

### Database Credentials
14. ✅ **MongoDB Connection String** - mongodb+srv://user:pass@...
15. ✅ **PostgreSQL Connection String** - postgresql://user:pass@...
16. ✅ **Redis Password**

### Monitoring & Observability
17. ✅ **Datadog API Key**
18. ✅ **New Relic License Key**

### Encryption & Authentication
19. ✅ **JWT Secret** (Custom pattern)
20. ✅ **RSA Private Key** - -----BEGIN RSA PRIVATE KEY-----

---

## 🎬 Demo Script: Secret Scanning in Pull Requests

### PART 1: Attempt to Push (With Push Protection)

#### Step 1: Try to Push This Branch

```bash
cd /Users/ankitasmac/Documents/kubstud/github/security-ghas-demo
git add .env src/main/java/com/lab/ghas/config/SecretsConfig.java
git commit -m "Add configuration files for production deployment"
git push -u origin demo/leaked-secrets
```

#### Expected Result (If Push Protection Enabled):

```
remote: 
remote: —— GitHub Push Protection ——————————————————————————
remote: 
remote: ❌ Push blocked: Secrets detected
remote: 
remote: GitHub Secret Scanning found 8 secrets:
remote: 
remote:   [1] GitHub Personal Access Token
remote:       Location: .env:2
remote:       
remote:   [2] AWS Access Key
remote:       Location: .env:6
remote:       
remote:   [3] Stripe API Secret Key
remote:       Location: .env:10
remote:       
remote:   [4] SendGrid API Key
remote:       Location: .env:13
remote:       
remote:   [5] Slack Webhook URL
remote:       Location: .env:19
remote:       
remote:   [6] Slack Bot Token
remote:       Location: .env:22
remote:       
remote:   ... and 2 more
remote: 
remote: Options:
remote:   1. Remove the secrets and push again
remote:   2. Use environment variables instead
remote:   3. Bypass protection (not recommended):
remote:      git push --push-option="secret-scanning=bypass"
remote: 
error: failed to push some refs
```

**Key talking point:**
> "Push protection just saved us! Before this code even reached GitHub, it was blocked. These 8 secrets would have been exposed in our git history forever. This is prevention, not detection."

---

### PART 2: Bypass to Create Demo PR (Demo Only!)

#### Step 2: Bypass Push Protection (For Demo Purposes Only)

```bash
# WARNING: Only do this for demo! Never bypass in production!
git push -u origin demo/leaked-secrets --push-option="secret-scanning=bypass"
```

Or, if that doesn't work:
1. Temporarily disable push protection in Settings
2. Push the branch
3. Re-enable push protection immediately

**Say to audience:**
> "I'm bypassing this ONLY to show you what happens when secrets reach GitHub. In production, you would NEVER do this. But let's see what the alerts look like..."

---

### PART 3: Create Pull Request & View Secret Alerts

#### Step 3: Create the PR

```
Go to: https://github.com/YOUR_USERNAME/security-ghas-demo/pulls
Click: "New pull request"
Base: main
Compare: demo/leaked-secrets
Title: "Add configuration files for production"
```

#### Step 4: View Secret Scanning Results in PR

In the PR, look for:
```
⚠️ Secret scanning found 8+ secrets in this pull request
```

**Click "View details"** to show:

##### Alert 1: GitHub Personal Access Token
```
File: .env:2
Secret: ghp_xF8k3L9mP2nQ5rT6vW7yZ8aB1cD4eF5gH6i
Type: GitHub Personal Access Token
Risk: Full repository access, code injection
Action: Revoke immediately at github.com/settings/tokens
```

##### Alert 2: AWS Access Keys
```
Files: .env:6-7
Secret: AKIAIOSFODNN7EXAMPLE
Type: AWS Access Key ID & Secret
Risk: Full AWS account access ($100K+ potential bills)
Action: Revoke in AWS IAM Console immediately
```

##### Alert 3: Stripe Live Key
```
File: .env:10
Secret: sk_live_51...
Type: Stripe Secret Key (LIVE MODE)
Risk: Unauthorized charges, refunds, customer data theft
Action: Roll key in Stripe Dashboard IMMEDIATELY
```

##### Alert 4: Database Credentials
```
Files: .env:23-24
Type: MongoDB & PostgreSQL connection strings
Risk: Full database access (read/write/delete)
Action: Rotate credentials, use secrets manager
```

---

### PART 4: Show Organization-Wide Impact

#### Step 5: Navigate to Security Overview

```
Go to: Security → Secret scanning
```

**Show:**
- Total secrets detected across all branches
- Secrets by type (AWS, GitHub, Stripe, etc.)
- Open vs resolved secrets
- High-priority secrets requiring immediate action

**Key talking point:**
> "This isn't just about one PR. Secret scanning monitors ALL branches, ALL commits, ALL files. Across your entire organization."

---

## 💰 Real-World Impact of Leaked Secrets

### Case Study 1: Uber ($148M Fine)
- **What leaked:** AWS keys in GitHub repo
- **Impact:** Attackers accessed 57 million customer records
- **Cost:** $148M in fines + legal fees
- **Prevention:** Secret scanning would have caught this

### Case Study 2: Code Spaces (Business Destroyed)
- **What leaked:** AWS credentials
- **Impact:** Attackers deleted all data and backups
- **Cost:** Company went out of business permanently
- **Prevention:** Push protection would have blocked the commit

### Case Study 3: Toyota (T-Mobile Breach)
- **What leaked:** Private key in public repo
- **Impact:** Email addresses and customer data exposed
- **Cost:** Multi-million dollar breach response
- **Prevention:** Secret scanning detects private keys

### Case Study 4: Cryptomining Attacks
- **What leaked:** AWS/Azure credentials
- **Impact:** $100K+ monthly cloud bills from unauthorized mining
- **Cost:** Varies, often $50K-$500K before detection
- **Prevention:** Real-time alerts stop charges within minutes

---

## 🎯 Demo Key Talking Points

### On Prevention:
> "Push protection stopped 8 secrets from entering git history. Once secrets are committed, they're in git history forever—even if you delete them. Prevention is everything."

### On Speed:
> "From commit to blocked push: 2 seconds. From leaked secret to attacker exploitation: 2-4 hours average. We're 3,600x faster than attackers."

### On Coverage:
> "GitHub knows 200+ secret patterns across AWS, Azure, GCP, GitHub, Stripe, and more. Plus you can add custom patterns for your internal systems."

### On Scale:
> "This works across 10,000 repos, 1 million commits, 100 developers. Zero configuration. Zero maintenance."

---

## 🔧 Why Secrets May Not Show in Your Demo

### Troubleshooting Checklist:

#### 1. Check Secret Scanning is Enabled
```bash
# Via API
curl -H "Authorization: token YOUR_TOKEN" \
  https://api.github.com/repos/OWNER/REPO \
  | jq '.security_and_analysis'

# Expected output:
{
  "secret_scanning": {
    "status": "enabled"
  },
  "secret_scanning_push_protection": {
    "status": "enabled"
  }
}
```

#### 2. Check Repository Visibility
```
Settings → General
If "Private": Requires GitHub Advanced Security
If "Public": Secret scanning is free
```

#### 3. Check for Push Protection Blocks
```bash
# Look in git push output for:
remote: —— GitHub Push Protection ——
```
If you see this, secrets were BLOCKED (even better than alerts!)

#### 4. Verify Secret Patterns Match GitHub's Database
Not all secrets trigger alerts. GitHub recognizes:
- ✅ AWS keys (AKIA...)
- ✅ GitHub tokens (ghp_, github_pat_)
- ✅ Stripe keys (sk_live_, sk_test_)
- ✅ SendGrid keys (SG....)
- ✅ Slack tokens (xoxb-, xoxp-)
- ❌ Generic passwords (not pattern-matched)
- ❌ Custom API keys (unless you add custom patterns)

#### 5. Check Commit History
Secrets must be in committed files, not just working directory:
```bash
git status  # Must show "Changes to be committed"
git log -1  # Verify commit includes secret files
```

---

## 🚀 Setting Up Secret Scanning (Step-by-Step)

### For Repository Administrators:

#### Step 1: Enable Secret Scanning
```
1. Go to: Repository → Settings → Security & analysis
2. Scroll to "Secret scanning"
3. Click "Enable"
4. Click "Enable" on "Push protection"
```

#### Step 2: Configure Alerts
```
1. Go to: Repository → Settings → Code security
2. Set: "Secret scanning alert" notification preferences
3. Add: Email, Slack, or webhook integrations
```

#### Step 3: Test It
```bash
# Create test branch
git checkout -b test/secret-detection

# Add fake secret
echo "GITHUB_TOKEN=ghp_TEST1234567890abcdefghij" > test.env

# Try to push
git add test.env
git commit -m "Test secret detection"
git push -u origin test/secret-detection

# Should be BLOCKED if push protection is on
# Or should create alert if only scanning is on
```

---

## 📊 Metrics to Show Success

### Before Secret Scanning:
- Secrets leaked: 5-10 per month
- Detection time: 30-90 days average
- Cost per incident: $50K-$500K
- Breaches per year: 1-2

### After Secret Scanning:
- Secrets leaked: 0 (blocked by push protection)
- Detection time: 2 seconds
- Cost per incident: $0
- Breaches per year: 0

### ROI Calculation:
```
Cost of one prevented breach: $150,000 (average)
Cost of GitHub Advanced Security: $49/user/month = $588/year
ROI: 25,500% or 255x return
```

---

## 🎁 Alternative: Secret Scanning Without Push Protection

If you can't see alerts because push protection is TOO effective:

### Option 1: Disable Push Protection Temporarily
```
Settings → Code security → Secret scanning
Uncheck: "Push protection"
Keep checked: "Secret scanning"
```
Now secrets will reach GitHub and create alerts instead of being blocked.

### Option 2: Use Secret Scanning on Existing Commits
Secret scanning automatically scans ALL existing commits, even from before it was enabled.

```
Go to: Security → Secret scanning
You'll see any historical secrets that were already committed
```

### Option 3: Demonstrate with Partner Token
Some secrets have special alert integrations:
- **GitHub tokens**: Show "Revoke" button
- **AWS keys**: Integration with AWS
- **Stripe keys**: Integration with Stripe

---

## 🔥 Live Demo Power Move

### The "Oh No" Moment

1. Show the `.env` file with 20 secrets
2. Say: "Imagine a developer accidentally commits this..."
3. Execute: `git push`
4. Watch: Push gets BLOCKED immediately
5. Say: "That just saved us $150,000 and our reputation."

**Then show the alternative:**
6. Say: "But what if push protection was off?"
7. Bypass to show alerts
8. Say: "Now we have 8 alerts. Better than nothing, but the damage is done—secrets are in git history. This is why push protection is critical."

---

## 📞 Call to Action

> "Every company has secrets. Every developer makes mistakes. The question isn't IF secrets will be committed—it's WHEN, and whether you'll catch them in time.
>
> Push protection prevents breaches before they happen. Secret scanning catches what slips through. Together, they've prevented thousands of security incidents across GitHub's customer base.
>
> The demo we just saw? That's happening in your organization right now. Let's make sure you catch it before attackers do."

**Next steps:**
1. Enable secret scanning on all repositories
2. Enable push protection (blocks secrets at push time)
3. Train developers on using secrets management tools
4. Set up alerts for security team
5. Conduct secret rotation for any historical leaks

---

## 🛠️ Technical Details (For Reference)

### Secret Patterns GitHub Detects (200+)

**Cloud Providers:**
- AWS, Azure, Google Cloud, DigitalOcean, Alibaba Cloud

**Version Control & CI/CD:**
- GitHub, GitLab, Bitbucket, CircleCI, Travis CI

**Payment Processors:**
- Stripe, PayPal, Square, Braintree

**Communication:**
- Slack, Twilio, SendGrid, Mailgun, Postmark

**Databases:**
- Connection strings with credentials for any database

**Monitoring:**
- Datadog, New Relic, Sentry, PagerDuty

**And 150+ more...**

### Custom Secret Patterns

You can add your own patterns:
```
Settings → Code security → Custom patterns
Add regex pattern for your internal tokens/keys
```

### API Access

Retrieve secrets programmatically:
```bash
curl -H "Authorization: Bearer TOKEN" \
  https://api.github.com/repos/OWNER/REPO/secret-scanning/alerts
```

Integrate with SIEM, SOAR, or ticketing systems.
