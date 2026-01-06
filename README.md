# 🔐 NovaSecurity

**NovaSecurity** ist ein modernes **Security- & Stability-Plugin** für **Paper & Spigot**.

Das Ziel des Projekts ist es, Minecraft-Server **stabil**, **sicher** und **resistent gegen Exploits, Floods und Crash-Angriffe** zu machen –  
ohne unnötigen Overhead, aggressive False-Positives oder AntiCheat-Verhalten.

> 🛡️ Security-first • ⚡ Performance-schonend • 🔌 Plugin-basiert • 🌐 Network-ready

---

## ✨ Features (aktuell)

### 🔐 Exploit & Flood Protection
- Book- & NBT-Exploit-Schutz
- Chat-Flood & Crash-Protection
- Command-Spam-Schutz
- Inventory- & Interact-Spam-Schutz
- Tab-Complete-Flood-Protection

### 🌍 Chunk & World Safety
- Chunk-Load-Spam-Erkennung
- Entity-Flood-Erkennung pro Chunk
- TileEntity-Flood-Erkennung (Hopper, Chests, Signs, etc.)
- **Per-World Whitelist & Blacklist** zur Vermeidung von False Positives

### ⚙️ Smart Enforcement
- Violation-Counter pro Spieler
- Eskalationssystem (WARN → KICK)
- Keine sofortigen Kicks bei Chunk-/Entity-Checks
- Konfigurierbare Actions (`CANCEL`, `WARN`, `KICK`)
- Admin- & System-Bypass

### 🔔 Administration & Monitoring
- Live **Admin-Alerts** bei allen Violations
- Alerts pro Admin ein-/ausschaltbar
- Persistente Violation-Speicherung (YAML)
- Debug-Modus für Feintuning

---

## ⚡ Performance & Sicherheit
- Kein permanenter Tick-Overhead
- Event-basierte Checks
- Keine Welt-Scans
- Keine Entity-/Block-Removals
- Sicherer Umgang mit Async-Events

NovaSecurity schützt **den Server**, nicht die Weltdateien.

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

## ⚙️ Konfigurations-Highlights

```yml
security:
  chat:
    max-length: 256
    action: WARN

  interact:
    max-per-second: 20
    action: CANCEL

  chunk-entities:
    max: 150
    action: WARN
    disabled-worlds:
      - creative
      - plots

alerts:
  enabled: true
