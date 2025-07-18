📄 README.md (Full Content)
markdown
Copy
Edit
# Java GUI Login/Register System

A simple Java Swing-based GUI application that allows users to register and log in.  
It stores user credentials in a text file with **SHA-256 password hashing** for security.

---

## ✨ Features

- ✅ User registration with confirmation
- ✅ Secure password hashing (SHA-256)
- ✅ User login verification
- ✅ Friendly GUI using Java Swing
- ✅ Data stored in `user_data/users.txt`
- ✅ Portable `.jar` support (coming soon)

---

## 📁 Folder Structure

JAVA_LOGIN/
├── bin/ # Compiled .class files
├── src/ # Source code files
│ ├── App.java
│ └── AuthSystem.java
├── user_data/ # Stores users.txt (created at runtime)
│ └── users.txt
├── .gitignore # Ignore bin/, class, and local data
└── README.md # Project instructions

yaml
Copy
Edit

---

## 🛠️ How to Compile and Run

### 💻 Compile:
```bash
javac -d bin src/*.java
▶️ Run:
bash
Copy
Edit
java -cp bin App
🔒 Password Security
Passwords are never stored in plain text.
This app uses SHA-256 hashing via MessageDigest to securely store credentials.

Example stored in users.txt:

makefile
Copy
Edit
myuser:5e884898da28047151d0e56f8dc6292773603d0d...
🚀 Planned Upgrades
 Add SHA-256 password hashing

 Switch to SQLite/MySQL for better scalability

 Improve UI using GridBagLayout

 Export runnable .jar file for easy use

📷 Screenshots

<img width="800" height="401" alt="image" src="https://github.com/user-attachments/assets/3de38c1e-2956-4c95-b8b4-f4cd5143aa2e" />


👨‍💻 Author
Vong Visal
GitHub Profile

📄 License
This project is open-source and available under the MIT License.

yaml
Copy
Edit

---

## ✅ Next Steps

1. **Copy the text above** into your `README.md`
2. **Commit & Push** it to your repo:

```bash
git add README.md
git commit -m "Update README with project overview and instructions"
git push
