# 🔐 NovaSecurity

NovaSecurity ist ein modernes Security- und Stability-Plugin für **Paper** und **Spigot**.

Ziel des Projekts ist es, Minecraft-Server **stabiler**, **sicherer** und **widerstandsfähiger gegen Exploits, Floods und Crash-Angriffe** zu machen – ohne unnötigen Overhead, aggressive False-Positives oder AntiCheat-Verhalten.

> 🛡️ Security-first · ⚡ Performance-schonend · 🔌 Plugin-basiert · 🌐 Network-ready

---

## ✨ Features (aktuell)

### 🔐 Exploit & Flood Protection
- Book- und NBT-Exploit-Schutz  
- Chat-Flood- und Crash-Protection  
- Command-Spam-Schutz  
- Inventory- und Interact-Spam-Schutz  
- Tab-Complete-Flood-Protection  

### 🌍 Chunk & World Safety
- Chunk-Load-Spam-Erkennung  
- Entity-Flood-Erkennung pro Chunk  
- TileEntity-Flood-Erkennung (Hopper, Chests, Signs, etc.)  
- Per-World Whitelist und Blacklist zur Vermeidung von False Positives  

### ⚙️ Smart Enforcement
- Violation-Counter pro Spieler  
- Eskalationssystem (WARN → KICK)  
- Keine sofortigen Kicks bei Chunk- oder Entity-Checks  
- Konfigurierbare Actions (`CANCEL`, `WARN`, `KICK`)  
- Admin- und System-Bypass  

### 🔔 Administration & Monitoring
- Live Admin-Alerts bei allen Violations  
- Alerts pro Admin ein- und ausschaltbar  
- Persistente Violation-Speicherung (YAML)  
- Debug-Modus für Feintuning  

---

## ⚡ Performance & Sicherheit
- Kein permanenter Tick-Overhead  
- Event-basierte Checks  
- Keine Welt-Scans  
- Keine Entity- oder Block-Removals  
- Sicherer Umgang mit Async-Events  

NovaSecurity schützt **den Server** – nicht die Weltdateien.

---

## 🧩 Unterstützte Plattformen
- ✅ **Paper** (empfohlen)  
- ✅ **Spigot**  
- ⏳ **Bungee / Velocity** (geplant)  

---

## 🛠️ Installation
1. Lade die neueste `NovaSecurity.jar` herunter  
2. Lege sie in den `plugins/`-Ordner deines Servers  
3. Starte den Server  
4. Passe die Konfiguration unter `plugins/NovaSecurity/config.yml` an  

---

## 📊 Commands
- `/novasecurity` – Plugin-Info  
- `/novasecurity reload` – Konfiguration neu laden  
- `/novasecurity debug` – Debug-Modus umschalten  
- `/novasecurity status <player>` – Violation-Status anzeigen  
- `/novasecurity alerts` – Admin-Alerts togglen  

---

## 🔑 Permissions
- `novasecurity.admin` – Vollzugriff  
- `novasecurity.alerts` – Admin-Alerts empfangen  
- `novasecurity.bypass` – Alle Checks umgehen  

---

## 🧠 Projektphilosophie
NovaSecurity verfolgt einen klaren Ansatz:

- Stabilität vor Bestrafung  
- Chunk- und Entity-Checks sind *Indikatoren*, keine Beweise  
- Eskalation statt aggressiver Kicks  
- Kein AntiCheat, sondern reiner Server-Schutz  
- Saubere, wartbare Architektur  

---

## 🗺️ Roadmap
- Bungee / Velocity Modul  
- Discord / Webhook Alerts  
- Erweiterte Attribution (Verursacher-Erkennung)  
- Optionales SQLite-Backend  

---

## 📄 Lizenz
MIT License – frei nutzbar, modifizierbar und weiterverteilbar.

---

## 🚀 Vision
NovaSecurity soll eine zuverlässige Sicherheits-Basis für Minecraft-Server jeder Größe werden –  
von kleinen Community-Servern bis hin zu größeren Netzwerken.

Built with ❤️ & Java.
